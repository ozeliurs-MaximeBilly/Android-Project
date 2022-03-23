package fr.ozeliurs.td1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ConverterListener {

    public float rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView taux = (TextView) findViewById(R.id.taux);
        TextView result = (TextView) findViewById(R.id.result);
        EditText input = (EditText) findViewById(R.id.input);
        Button convert_button = (Button) findViewById(R.id.convert_button);

        // Creation du converter
        Converter converter = new ConverterGeoPlugin(this);
        converter.init();
        converter.setListener(this);

        // Listener on Button + Function launched on button
        convert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float in = Float.parseFloat(input.getText().toString());

                result.setText(String.format("%.2f ₽",converter.convert(in)));
            }
        });
    }

    @Override
    public void onRateUpdated(float rate) {
        this.rate = rate;
        TextView taux = (TextView) findViewById(R.id.taux);
        taux.setText(String.format("%.2f ₽",rate));
    }

    @Override
    public void onError(String message) {

    }
}
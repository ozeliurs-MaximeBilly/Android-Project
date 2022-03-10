package fr.ozeliurs.td1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private float eur_rub = 133.0119f;

    private String url = "http://geoplugin.net/json.gp?base_currency=RUB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = Volley.newRequestQueue(this);

        TextView taux = (TextView) findViewById(R.id.taux);

        // Request currency rate
        JsonObjectRequest stringRequest = new JsonObjectRequest(
                Request.Method.GET,
                this.url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            eur_rub = (float) (1/response.getDouble("geoplugin_currencyConverter"));
                            taux.setText(String.format("%.2f",eur_rub)+" ₽");
                        } catch (JSONException e) {}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {}
                }
        );

        queue.add(stringRequest);

        // Listener on Button + Function launched on button
        Button convert_button = (Button) findViewById(R.id.convert_button);
        convert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView result = (TextView) findViewById(R.id.result);
                EditText input = (EditText) findViewById(R.id.input);
                float in = (float) Float.parseFloat(input.getText().toString());

                in = in * eur_rub;

                result.setText(String.format("%.2f",in)+" ₽");
            }
        });
    }
}
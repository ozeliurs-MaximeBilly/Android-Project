package fr.ozeliurs.td1;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ConverterGeoPlugin extends Converter {
    private float rate;
    private RequestQueue queue;
    private ConverterListener listener;

    public ConverterGeoPlugin(MainActivity mainActivity) {
        queue = Volley.newRequestQueue(mainActivity);
    }

    @Override
    public void init() {
        JsonObjectRequest stringRequest = new JsonObjectRequest(
                Request.Method.GET,
                "http://geoplugin.net/json.gp?base_currency=RUB",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            rate = (float) (1/response.getDouble("geoplugin_currencyConverter"));

                        } catch (JSONException e) {rate = 1;}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {rate=1;}
                }
        );

        queue.add(stringRequest);
    }

    public float getRate() {
        return rate;
    }

    public float convert(float in) {
        return rate*in;
    }

    public void setListener(ConverterListener listener) {
        this.listener = listener;
    }
}

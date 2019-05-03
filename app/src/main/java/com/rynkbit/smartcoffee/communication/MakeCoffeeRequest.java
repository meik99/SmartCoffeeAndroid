package com.rynkbit.smartcoffee.communication;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MakeCoffeeRequest {

    private static final String REQUEST_URL = "http://192.168.178.24:5000/coffee";

    private MakeCoffeeListener listener;

    public void sendMakeCoffeeRequest(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                REQUEST_URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(listener != null){
                            listener.onResponse(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(listener != null){
                            listener.onError(error);
                        }
                    }
                }
        );
        queue.add(request);
        queue.start();
    }

    public void setListener(MakeCoffeeListener listener){
        this.listener = listener;
    }
}

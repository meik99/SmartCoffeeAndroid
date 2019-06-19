package com.rynkbit.smartcoffee.communication;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class GetAlarmsRequest {
    private GetAlarmRequestListener mListener;

    public void setListener(GetAlarmRequestListener listener){
        this.mListener = listener;
    }


    public void sendGetAlarmsRequest(Context context){
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                CoffeeRequestConstants.REQUEST_URL + CoffeeRequestConstants.ALARM_ENDPOINT,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(mListener != null){
                            mListener.onResponse(new JsonAlarmConverter()
                                    .convertArrayToAlarmList(response));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                        if(mListener != null){
                            mListener.onError(error);
                        }
                    }
                }
        );
        queue.start();
        queue.add(jsonArrayRequest);
    }
}

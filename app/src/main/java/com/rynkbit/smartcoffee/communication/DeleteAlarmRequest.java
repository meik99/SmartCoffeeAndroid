package com.rynkbit.smartcoffee.communication;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rynkbit.smartcoffee.entity.Alarm;

public class DeleteAlarmRequest {
    private DeleteAlarmRequestListener mListener;

    public void setListener(DeleteAlarmRequestListener listener){
        this.mListener = listener;
    }


    public void sendDeleteAlarmsRequest(Context context, Alarm alarm){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE,
                CoffeeRequestConstants.REQUEST_URL +
                        CoffeeRequestConstants.ALARM_ENDPOINT + "?id=" + alarm.getId(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(mListener != null){
                            mListener
                                    .onResponse(
                                            response
                                    );
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(mListener != null){
                            mListener.onError(error);
                        }
                    }
                }
        );
        queue.start();
        queue.add(stringRequest);
    }
}

package com.rynkbit.smartcoffee.communication;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rynkbit.smartcoffee.entitiy.Alarm;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

public class PostAlarmRequest {
    private PostAlarmRequestListener mListener;

    public void setListener(PostAlarmRequestListener listener){
        this.mListener = listener;
    }


    public void sendPostAlarmsRequest(Context context, Alarm alarm){
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(
                Request.Method.POST,
                CoffeeRequestConstants.REQUEST_URL + CoffeeRequestConstants.ALARM_ENDPOINT,
                new JsonAlarmConverter().convertAlarmToObject(alarm),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(mListener != null){
                            try {
                                mListener
                                        .onResponse(
                                                new JsonAlarmConverter().convertObjectToAlarm(response)
                                        );
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
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
        queue.add(jsonArrayRequest);
    }
}

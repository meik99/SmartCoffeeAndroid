package com.rynkbit.smartcoffee.communication.request;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rynkbit.smartcoffee.communication.CoffeeRequestConstants;
import com.rynkbit.smartcoffee.communication.JsonAlarmConverter;
import com.rynkbit.smartcoffee.communication.listener.PostAlarmRequestListener;
import com.rynkbit.smartcoffee.entitiy.Alarm;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

public class PostAlarmRequest extends BaseRequest{
    private PostAlarmRequestListener mListener;

    public PostAlarmRequest(Context context) {
        super(context);
    }

    public void setListener(PostAlarmRequestListener listener){
        this.mListener = listener;
    }


    public void sendPostAlarmsRequest(Context context, Alarm alarm, boolean isUpdate){
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(
                isUpdate == false ? Request.Method.POST : Request.Method.PUT,
                getRequestUrl() + CoffeeRequestConstants.ALARM_ENDPOINT,
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

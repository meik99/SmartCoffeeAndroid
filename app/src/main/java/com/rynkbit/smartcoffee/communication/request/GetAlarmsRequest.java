package com.rynkbit.smartcoffee.communication.request;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.rynkbit.smartcoffee.communication.CoffeeRequestConstants;
import com.rynkbit.smartcoffee.communication.JsonAlarmConverter;
import com.rynkbit.smartcoffee.communication.listener.GetAlarmRequestListener;

import org.json.JSONArray;

public class GetAlarmsRequest extends BaseRequest {
    private GetAlarmRequestListener mListener;

    public GetAlarmsRequest(Context context) {
        super(context);
    }

    public void setListener(GetAlarmRequestListener listener){
        this.mListener = listener;
    }


    public void sendGetAlarmsRequest(Context context){
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                getRequestUrl() + CoffeeRequestConstants.ALARM_ENDPOINT,
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

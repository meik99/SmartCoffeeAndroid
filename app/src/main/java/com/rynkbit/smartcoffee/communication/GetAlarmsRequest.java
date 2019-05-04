package com.rynkbit.smartcoffee.communication;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.rynkbit.smartcoffee.entitiy.Alarm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

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
                        List<Alarm> alarms = new LinkedList<>();

                        for(int i = 0; i < response.length(); i++){
                            try {
                                JSONObject alarmObject = response.getJSONObject(i);
                                Alarm alarm = new Alarm();

                                alarm.setId(alarmObject.getInt("id"));
                                alarm.setHour(alarmObject.getInt("hour"));
                                alarm.setMinute(alarmObject.getInt("minute"));
                                alarm.setName(alarmObject.getString("name"));
                                alarm.setLastActivated(
                                        new SimpleDateFormat(
                                                Alarm.DATE_PATTERN,
                                                Locale.getDefault())
                                            .parse(
                                                    alarmObject.getString("last_activated")
                                                    .substring(
                                                            0,
                                                            alarmObject
                                                                    .getString(
                                                                            "last_activated"
                                                                    )
                                                                    .length() - 3
                                                    )
                                            )
                                );
                                alarms.add(alarm);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                i = response.length();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }

                        if(mListener != null){
                            mListener.onResponse(alarms);
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

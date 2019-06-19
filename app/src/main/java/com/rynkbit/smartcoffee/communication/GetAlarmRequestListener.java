package com.rynkbit.smartcoffee.communication;

import com.android.volley.VolleyError;
import com.rynkbit.smartcoffee.entity.Alarm;

import java.util.List;

public interface GetAlarmRequestListener {
    void onResponse(List<Alarm> alarms);
    void onError(VolleyError error);
}

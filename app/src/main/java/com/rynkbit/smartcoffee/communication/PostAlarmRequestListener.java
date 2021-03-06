package com.rynkbit.smartcoffee.communication;

import com.android.volley.VolleyError;
import com.rynkbit.smartcoffee.entity.Alarm;

public interface PostAlarmRequestListener {
    void onResponse(Alarm alarm);
    void onError(VolleyError error);
}

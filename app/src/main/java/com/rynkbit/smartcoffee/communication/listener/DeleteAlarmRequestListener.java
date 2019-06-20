package com.rynkbit.smartcoffee.communication.listener;

import com.android.volley.VolleyError;

public interface DeleteAlarmRequestListener {
    void onResponse(String response);
    void onError(VolleyError error);
}

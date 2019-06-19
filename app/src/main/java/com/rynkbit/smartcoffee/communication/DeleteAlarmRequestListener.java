package com.rynkbit.smartcoffee.communication;

import com.android.volley.VolleyError;

public interface DeleteAlarmRequestListener {
    void onResponse(String response);
    void onError(VolleyError error);
}

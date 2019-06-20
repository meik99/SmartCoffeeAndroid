package com.rynkbit.smartcoffee.communication.listener;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface MakeCoffeeListener {
    void onResponse(JSONObject response);

    void onError(VolleyError error);
}

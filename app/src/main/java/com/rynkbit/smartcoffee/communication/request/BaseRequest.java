package com.rynkbit.smartcoffee.communication.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.rynkbit.smartcoffee.R;

import java.util.Locale;

public abstract class BaseRequest {
    private String requestUrl;
    private String protocol = "http://";
    private String port = "5000";

    public BaseRequest(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        setRequestUrl(
            String.format(Locale.getDefault(), "%s%s:%s",
                    getProtocol(),
                    preferences.getString(
                            context.getString(R.string.smartcoffee_ip),
                            context.getString(R.string.smartcoffee_ip_value)
                    ),
                    getPort()
            )
        );
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
}

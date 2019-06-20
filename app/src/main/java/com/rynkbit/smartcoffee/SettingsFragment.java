package com.rynkbit.smartcoffee;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;

import com.android.volley.VolleyError;
import com.google.android.material.snackbar.Snackbar;
import com.rynkbit.smartcoffee.communication.listener.GetAlarmRequestListener;
import com.rynkbit.smartcoffee.communication.request.GetAlarmsRequest;
import com.rynkbit.smartcoffee.entitiy.Alarm;

import java.util.List;
import java.util.Objects;


public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {


    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager()
                .getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager()
                .getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        GetAlarmsRequest request = new GetAlarmsRequest(getContext());

        request.setListener(new GetAlarmRequestListener() {
            @Override
            public void onResponse(List<Alarm> alarms) {
                Snackbar.make(
                        Objects.requireNonNull(getView()),
                        "Successfully connected to SmartCoffee coffee maker",
                        Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onError(VolleyError error) {
                Snackbar.make(
                        Objects.requireNonNull(getView()),
                        "Could not connect to SmartCoffee coffee maker",
                        Snackbar.LENGTH_SHORT).show();
            }
        });

        request.sendGetAlarmsRequest(getContext());

    }
}

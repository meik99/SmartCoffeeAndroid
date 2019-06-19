package com.rynkbit.smartcoffee;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.google.android.material.snackbar.Snackbar;
import com.rynkbit.smartcoffee.communication.PostAlarmRequest;
import com.rynkbit.smartcoffee.communication.PostAlarmRequestListener;
import com.rynkbit.smartcoffee.entitiy.Alarm;

public class EditAlarmViewModel extends ViewModel {
    private Alarm mAlarm = new Alarm();

    public void setTime(int hourOfDay, int minute) {
        mAlarm.setHour(hourOfDay);
        mAlarm.setMinute(minute);
    }

    public void setName(String name) {
        mAlarm.setName(name);
    }

    public void setAlarm(Alarm Alarm) {
        this.mAlarm = Alarm;
    }

    public void saveAlarm(Context context, PostAlarmRequestListener listener){
        PostAlarmRequest request = new PostAlarmRequest();

        request.setListener(listener);
        request.sendPostAlarmsRequest(context, mAlarm, mAlarm.getId() > 0);
    }

    public Alarm getAlarm() {
        return mAlarm;
    }
}

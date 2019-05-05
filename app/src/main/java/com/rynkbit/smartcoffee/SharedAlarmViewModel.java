package com.rynkbit.smartcoffee;

import androidx.lifecycle.ViewModel;

import com.rynkbit.smartcoffee.entitiy.Alarm;

public class SharedAlarmViewModel extends ViewModel {
    private Alarm sharedAlarm = new Alarm();

    public Alarm getSharedAlarm() {
        return sharedAlarm;
    }

    public void setSharedAlarm(Alarm sharedAlarm) {
        this.sharedAlarm = sharedAlarm;
    }
}

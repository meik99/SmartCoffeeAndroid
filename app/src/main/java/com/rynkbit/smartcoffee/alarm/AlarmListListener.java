package com.rynkbit.smartcoffee.alarm;

import com.rynkbit.smartcoffee.entitiy.Alarm;

public interface AlarmListListener {
    void onClick(Alarm alarm);
    void onLongClick(Alarm alarm);
}

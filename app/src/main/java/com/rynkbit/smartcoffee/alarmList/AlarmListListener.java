package com.rynkbit.smartcoffee.alarmList;

import com.rynkbit.smartcoffee.entity.Alarm;

public interface AlarmListListener {
    void onClick(Alarm alarm);
    void onLongClick(Alarm alarm);
}

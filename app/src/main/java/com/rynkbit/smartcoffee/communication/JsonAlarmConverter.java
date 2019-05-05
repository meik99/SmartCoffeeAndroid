package com.rynkbit.smartcoffee.communication;

import com.rynkbit.smartcoffee.entitiy.Alarm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class JsonAlarmConverter {
    public Alarm convertObjectToAlarm(JSONObject alarmObject) throws JSONException, ParseException {
        Alarm alarm = new Alarm();

        alarm.setId(alarmObject.getInt("id"));
        alarm.setHour(alarmObject.getInt("hour"));
        alarm.setMinute(alarmObject.getInt("minute"));
        alarm.setName(alarmObject.getString("name"));
        alarm.setLastActivated(
                new SimpleDateFormat(
                        Alarm.DATE_PATTERN,
                        Locale.getDefault())
                        .parse(
                                alarmObject.getString("last_activated")
                                        .substring(
                                                0,
                                                alarmObject
                                                        .getString(
                                                                "last_activated"
                                                        )
                                                        .length() - 3
                                        )
                        )
        );

        return alarm;
    }

    public List<Alarm> convertArrayToAlarmList(JSONArray alarmArray){
        List<Alarm> alarms = new LinkedList<>();

        for(int i = 0; i < alarmArray.length(); i++){
            try {
                JSONObject alarmObject = alarmArray.getJSONObject(i);
                Alarm alarm = new JsonAlarmConverter()
                        .convertObjectToAlarm(alarmObject);
                alarms.add(alarm);
            } catch (JSONException e) {
                e.printStackTrace();
                i = alarmArray.length();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return alarms;
    }

    public JSONObject convertAlarmToObject(Alarm alarm) {
        JSONObject alarmObject = new JSONObject();
        try {
            alarmObject.put("name", alarm.getName());
            alarmObject.put("hour", alarm.getHour());
            alarmObject.put("minute", alarm.getMinute());
            alarmObject.put("id", alarm.getId());
            alarmObject.put("last_activated", alarm.getLastActivatedAsString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return alarmObject;
    }
}

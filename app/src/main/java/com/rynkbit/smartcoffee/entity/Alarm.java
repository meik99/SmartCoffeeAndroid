package com.rynkbit.smartcoffee.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Alarm implements Parcelable{
    private int id;
    private int hour;
    private int minute;
    private String name;
    private Date lastActivated;

    public static final String DATE_PATTERN = "y-M-d H:m:s.S";

    private SimpleDateFormat alarmDateFormat = new SimpleDateFormat(
            DATE_PATTERN, Locale.getDefault()
    );

    public Alarm(int id, int hour, int minute, String name, Date last_activated) {
        this.id = id;
        this.hour = hour;
        this.minute = minute;
        this.name = name;
        this.lastActivated = last_activated;
    }

    public Alarm() {
        this(0, 6, 0, "Alarm", new Date());
    }

    protected Alarm(Parcel in) {
        id = in.readInt();
        hour = in.readInt();
        minute = in.readInt();
        name = in.readString();
        try {
            lastActivated = alarmDateFormat.parse(in.readString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static final Parcelable.Creator<Alarm> CREATOR = new Parcelable.Creator<Alarm>() {
        @Override
        public Alarm createFromParcel(Parcel in) {
            return new Alarm(in);
        }

        @Override
        public Alarm[] newArray(int size) {
            return new Alarm[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastActivated() {
        return lastActivated;
    }

    public void setLastActivated(Date lastActivated) {
        if(lastActivated == null){
            throw new NullPointerException("lastActivated must not be null!");
        }

        this.lastActivated = lastActivated;
    }

    public String getLastActivatedAsString(){
        return alarmDateFormat.format(getLastActivated());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(hour);
        dest.writeInt(minute);
        dest.writeString(name);
        dest.writeString(alarmDateFormat.format(lastActivated));
    }
}

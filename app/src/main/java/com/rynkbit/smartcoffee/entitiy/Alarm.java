package com.rynkbit.smartcoffee.entitiy;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Alarm implements Parcelable {
    public static final String DATE_PATTERN = "y-M-d H:m:s.S";

    private SimpleDateFormat alarmDateFormat = new SimpleDateFormat(
            DATE_PATTERN, Locale.getDefault()
    );

    private int id;
    private int hour;
    private int minute;
    private String name;
    private Date lastActivated;

    public Alarm(int id, int hour, int minute, String name, Date last_activated) {
        this.id = id;
        this.hour = hour;
        this.minute = minute;
        this.name = name;
        this.lastActivated = last_activated;
    }

    public Alarm() {
        this(0, -1, -1, "Alarm", new Date());
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

    public static final Creator<Alarm> CREATOR = new Creator<Alarm>() {
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
            throw new NullPointerException("lastActivated must not be null");
        }

        this.lastActivated = lastActivated;
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

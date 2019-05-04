package com.rynkbit.smartcoffee.entitiy;

import java.util.Date;

public class Alarm {
    public static final String DATE_PATTERN = "y-M-d H:m:s.S";

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
        this.lastActivated = lastActivated;
    }
}

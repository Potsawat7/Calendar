package models;

import java.time.Month;

public class Appointment {
    public String title;
    private String location;

    private String hour;
    private String min;
    private String merid;
    private Month month;
    private int day, year;
    public Appointment(String title, int day, Month month, int year, String hour, String min, String merid, String location) {
        this.title = title;
        this.location = location;
        this.hour = hour;
        this.min = min;
        this.merid = merid;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getHour() {
        return hour;
    }

    public String getMin() {
        return min;
    }

    public String getMerid() {
        return merid;
    }

    public String getMonth() {
        return month.toString();
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

}

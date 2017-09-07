package models;

import java.time.Month;

public class Appointment {


    public String title;
    private String location;

    private String date;
    private String hour;
    private String min;
    private String merid;
    private Month month;
    private int day, year;
    public Appointment(String title, String date,  String location) {
        this.date = date;
        this.title = title;
        this.location = location;

    }



}

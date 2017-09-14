package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Date;

public class Appointment {


    public String title;
    private String location;
//    private DateFormat format;
//    private Date dat;

    private String date;
//    private String hour;
//    private String min;
//    private String merid;
//    private Month month;
//    private int day, year;

    public Appointment(String title, String date,  String location) throws ParseException {
        this.date = date;
        this.title = title;
        this.location = location;

//        format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//        dat = format.parse(date);

    }


}

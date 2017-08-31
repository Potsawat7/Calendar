package models;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;

public class Events  {
    public String show ="";
    public ArrayList<Appointment> event =  new ArrayList<>();


    public void addApp(Appointment appointment) {
        event.add(appointment);
    }

    public String showEvent() {
        String string="";
        for(int i = 0 ; i < event.size();i++){
            string = (i+1)+". " + "Title: " + event.get(i).getTitle() + "\n"
                    +"   " + "Date: " + event.get(i).getDay()+" "+event.get(i).getMonth()+" "+event.get(i).getYear()+"\n"
                    +"   " + "Time: " + event.get(i).getHour() + " :: " + event.get(i).getMin()+ " " + event.get(i).getMerid()+"\n"
                    +"   " + "Location: "  + event.get(i).getLocation() +"\n";

        }show+=string;
        return show;
    }
//    public String toString(){
//        String s = "";
//        for(int i = 0 ; i < event.size();i++){
//            s =  event.get(i).getTitle()
//                   + event.get(i).getDay()+event.get(i).getMonth()+event.get(i).getYear()
//                  + event.get(i).getHour() + event.get(i).getMin()+ event.get(i).getMerid()
//                    + event.get(i).getLocation();
//        }
//        return s;
//    }
}

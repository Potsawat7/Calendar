package models;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;

public class Events  {
    public String show ="";
    public ArrayList<Appointment> event =  new ArrayList<>();


    public void addApp(Appointment appointment) {
        event.add(appointment);
    }


//    public int getIdFromSize(){
//        return event.size()+1;
//    }

}

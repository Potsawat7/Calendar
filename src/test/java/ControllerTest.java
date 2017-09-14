import controllers.Controller;
import models.Appointment;
import models.Events;
import org.junit.Test;

import java.text.ParseException;
import java.time.Month;

import static org.junit.Assert.*;

public class ControllerTest {

    Events events;
    Appointment appointment;

    @Test
    public void testCalendar() throws ParseException {

        events = new Events();

        appointment = new Appointment("ประชุมสโม","1 Month.JANUARY 2017 1:00 AM","smo sci");
        events.addApp(appointment);
        assertEquals(events.event.size(),1,0);

        assertEquals(events.event.get(0).title,"ประชุมสโม");

    }
}
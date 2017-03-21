import copenhagen.Clock;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClockTest {
    
    @Test
    public void testOutOfTime() {
        Clock cl = new Clock(0,0,0);
        boolean actual = cl.outOfTime();
        assertEquals(true, actual);
    }
    
    @Test
    public void testGetTime() {
        Clock cl = new Clock(0,0,59);
        String actual = cl.getTime();
        assertEquals(actual, "00:00:59");
    }
    
    @Test
    public void testDecrChangesSecond() {
        Clock cl = new Clock(0,0,59);
        cl.decr();
        String actual = cl.getTime();
        assertEquals(actual, "00:00:58");
    }

    @Test
    public void testDecrChangesMinute() {
        Clock cl = new Clock(0,1,0);
        cl.decr();
        String actual = cl.getTime();
        assertEquals(actual, "00:00:59");
    }
    
    @Test
    public void testDecrChangesHour() {
        Clock cl = new Clock(1,0,0);
        cl.decr();
        String actual = cl.getTime();
        assertEquals(actual, "00:59:59");
    }
    
    @Test
    public void testAddSecondsChangesSeconds() {
        Clock cl = new Clock(0,0,0);
        cl.addSeconds(2);
        String actual = cl.getTime();
        assertEquals(actual, "00:00:02");
    }
    
    @Test
    public void testAddSecondsChangesMinutes() {
        Clock cl = new Clock(0,0,59);
        cl.addSeconds(2);
        String actual = cl.getTime();
        assertEquals(actual, "00:01:01");
    }
    
    @Test
    public void testAddSecondsChangeHours() {
        Clock cl = new Clock(0,59,59);
        cl.addSeconds(2);
        String actual = cl.getTime();
        assertEquals(actual, "01:00:01");
    }
}

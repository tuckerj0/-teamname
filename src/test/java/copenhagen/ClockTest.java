import copenhagen.Clock;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClockTest {

    // This test checks that the correct value of true is returned when there is no more time left on a clock.
    @Test
    public void testOutOfTime() {
        Clock cl = new Clock(0,0,0);
        boolean actual = cl.outOfTime();
        assertEquals(true, actual);
    }

    // This test checks that retrieving the time returns the properly formatted string.
    @Test
    public void testGetTime() {
        Clock cl = new Clock(0,0,59);
        String actual = cl.getTime();
        assertEquals(actual, "00:00:59");
    }

    // This tests checks that the correct time is returned after it has be decremented by one second.
    @Test
    public void testDecrChangesSecond() {
        Clock cl = new Clock(0,0,59);
        cl.decr();
        String actual = cl.getTime();
        assertEquals(actual, "00:00:58");
    }

    // This tests checks that the correct time is returned after 1 minute has be decremented by one second.
    @Test
    public void testDecrChangesMinute() {
        Clock cl = new Clock(0,1,0);
        cl.decr();
        String actual = cl.getTime();
        assertEquals(actual, "00:00:59");
    }

    // This tests checks that the correct time is returned after 1 hour has be decremented by one second.
    @Test
    public void testDecrChangesHour() {
        Clock cl = new Clock(1,0,0);
        cl.decr();
        String actual = cl.getTime();
        assertEquals(actual, "00:59:59");
    }

    // This tests checks that the correct time is returned after adding 2 seconds.
    @Test
    public void testAddSecondsChangesSeconds() {
        Clock cl = new Clock(0,0,0);
        cl.addSeconds(2);
        String actual = cl.getTime();
        assertEquals(actual, "00:00:02");
    }

    // This tests checks that the correct time is returned after 2 seconds are added causing the timer to go above
    // 1 minute and above.
    @Test
    public void testAddSecondsChangesMinutes() {
        Clock cl = new Clock(0,0,59);
        cl.addSeconds(2);
        String actual = cl.getTime();
        assertEquals(actual, "00:01:01");
    }

    // This tests checks that the correct time is returned after 2 seconds are added causing the timer to go above
    // 1 hour and above.
    @Test
    public void testAddSecondsChangeHours() {
        Clock cl = new Clock(0,59,59);
        cl.addSeconds(2);
        String actual = cl.getTime();
        assertEquals(actual, "01:00:01");
    }
}

import org.junit.Test;
import static org.junit.Assert.*;

import copenhagen.Timer;
public class TimerTest {
    
    @Test
    public void testTimerInitialization() {
        Timer t = new Timer(10);
        t.start();
    }

}

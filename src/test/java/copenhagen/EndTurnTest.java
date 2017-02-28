import org.junit.Test;
import static org.junit.Assert.*;

import copenhagen.hnefatafl;

public class EndTurnTest {
    @Test
    public void testEndTurn() {
        int turnCount = 0;
        assertEquals(endturn(), 1);
    }

    @Test
    public void testEndTurn() {
        int turnCount = 100;
        assertEquals(endturn(), 101);
    }
}

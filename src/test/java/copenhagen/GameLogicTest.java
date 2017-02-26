import org.junit.Test;
import static org.junit.Assert.*;

import copenhagen.GameLogic;

public class GameLogicTest {

    // This test is to ensure that the game board array is created correctly with all the right pieces in the correct
    // spot.
    @Test
    public void testSetStartingPiecesIfSizeIsEleven() {
        int size = 11;

        char[][] expected = new char [size][size];
        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[i].length; j++) {
                expected[i][j] = '0';
            }
        }
        expected[0][3] = expected[0][4] = expected[0][5] = expected[0][6] = expected[0][7] = expected[1][5] = 'b';
        expected[3][0] = expected[4][0] = expected[5][0] = expected[6][0] = expected[7][0] = expected[5][1] = 'b';
        expected[10][3] = expected[10][4] = expected[10][5] = expected[10][6] = expected[10][7] = expected[9][5] = 'b';
        expected[3][10] = expected[4][10] = expected[5][10] = expected[6][10] = expected[7][10] = expected[5][9] = 'b';
        expected[3][5] = expected[4][4] = expected[4][5] = expected[4][6] = expected[5][3] = expected[5][4] = 'w';
        expected[5][6] = expected[5][7] = expected[6][4] = expected[6][5] = expected[6][6] = expected[7][5] = 'w';
        expected[0][0] = expected[0][10] = expected[10][0] = expected[10][10] = expected[5][5] = 'c';
        expected[5][5] = 'k';

        char[][] actual = GameLogic.setStartingPieces(size);

        // check each individual position to ensure correct value
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.println("Expected: " + expected[i][j]);
                System.out.println("Actual: " + actual[i][j]);
                assertEquals(expected[i][j], actual[i][j]);
            }
        }
    }

    // This test checks to see if the game board array is filled with 0s if the size of the board is not 11
    // or potentially 9 if that feature is added later.
    @Test
    public void testSetStartingPiecesIfUnvalidSize() {
        int size = 1;

        char[][] expected = new char [size][size];
        expected[0][0] = '0';

        char[][] actual = GameLogic.setStartingPieces(size);
        assertEquals(expected[0][0], actual[0][0]);
    }
}

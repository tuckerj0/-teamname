import org.junit.Test;
import static org.junit.Assert.*;

import copenhagen.SaveAndLoad;

public class SaveAndLoadTest {

	/**
     * These functions test the checkState function from saveAndLoad
     */
	 /*
	@Test
    public void testCheckStateIfValid1() {
        String layout = "c00bbbbb00c00000b0000000000000000b0000w0000bb000www000bbb0wwkww0bbb000www000bb0000w0000b0000000000000000b00000c00bbbbb00c";
        int turnCount = 1;
        char turn = 'b';
		String aTime = "11:11:11";
		String dTime = "11:11:11";
        assertEquals(true, SaveAndLoad.checkState(layout,turn,turnCount, aTime, dTime));
    }

    // This is another test which checks that the checkState function returns true when given valid data.
    @Test
    public void testCheckStateIfValid2() {
        String layout = "c00b0b0000c0000000000000000000000000b0bb000bbw00b0b000bbbw0wkb0wbb000bww00000bw000000b000000000w00000000b0b000c000bb0000c";
        int turnCount = 25;
        char turn = 'w';
		String aTime = "11:11:11";
		String dTime = "11:11:11";
        assertEquals(true, SaveAndLoad.checkState(layout,turn,turnCount, aTime, dTime));
    }

    // This test checks that the checkState function returns true when given valid data that has the absolute bare
    // minimum to have the test still pass.
    @Test
    public void testCheckStateIfValidMinimumAmount() {
        String layout = "c000000000c00000b00000000000000000000000000bb000000000bbb000k0000000000000000000000000000000000000000000000000c000000000c";
        int turnCount = 1;
        char turn = 'b';
		String aTime = "11:11:11";
		String dTime = "11:11:11";
        assertEquals(true, SaveAndLoad.checkState(layout,turn,turnCount, aTime, dTime));
    }

    // This test checks that the checkState function will return false when given an empty string layout.
    @Test
    public void testCheckStateIfEmptyLayout() {
        String layout = "";
        int turnCount = 1;
        char turn = 'b';
		String aTime = "11:11:11";
		String dTime = "11:11:11";
        assertEquals(false, SaveAndLoad.checkState(layout, turn, turnCount, aTime, dTime));
    }

    // This test checks that the checkState function returns false when given an invalid turn count.
    @Test
    public void testCheckStateIfInvalidTurnCount() {
        String layout = "c00bbbbb00c00000b0000000000000000b0000w0000bb000www000bbb0wwkww0bbb000www000bb0000w0000b0000000000000000b00000c00bbbbb00c";
        int turnCount = -1;
        char turn = 'b';
		String aTime = "11:11:11";
		String dTime = "11:11:11";
        assertEquals(false, SaveAndLoad.checkState(layout,turn,turnCount, aTime, dTime));
    }

    // This test checks that the checkState function returns false when given an invalid turn. It has to be either 'w'
    // (Defenders) or 'b' (Attackers).
    @Test
    public void testCheckStateIfInvalidTurn() {
        String layout = "c00bbbbb00c00000b0000000000000000b0000w0000bb000www000bbb0wwkww0bbb000www000bb0000w0000b0000000000000000b00000c00bbbbb00c";
        int turnCount = 1;
        char turn = 'k';
		String aTime = "11:11:11";
		String dTime = "11:11:11";
        assertEquals(false, SaveAndLoad.checkState(layout,turn,turnCount, aTime, dTime));
    }

    // This test checks that the checkState function returns false when given an invalid layout with too many characters
    // in the string.
    @Test
    public void testCheckStateIfLayoutHasTooManyCharacters() {
        String layout = "0c00b0b0000c0000000000000000000000000b0bb000bbw00b0b000bbbw0wkb0wbb000bww00000bw000000b000000000w00000000b0b000c000bb0000c";
        int turnCount = 25;
        char turn = 'w';
		String aTime = "11:11:11";
		String dTime = "11:11:11";
        assertEquals(false, SaveAndLoad.checkState(layout,turn,turnCount, aTime, dTime));
    }

    // This test checks that the checkState function returns false when given an invalid layout with an invalid
    // character. This includes anything that is not a '0', 'c', 'b', 'w', or 'k'.
    @Test
    public void testCheckStateIfLayoutHasAnInvalidCharacter() {
        String layout = "c00b0b0000c0000000000000000000000000b0bb000tbw00b0b000bbbw0wkb0wbb000bww00000bw000000b000000000w00000000b0b000c000bb0000c";
        int turnCount = 25;
        char turn = 'w';
		String aTime = "11:11:11";
		String dTime = "11:11:11";
        assertEquals(false, SaveAndLoad.checkState(layout,turn,turnCount, aTime, dTime));
    }

    // This test checks that the checkState function returns false when given an invalid layout with no king piece.
    @Test
    public void testCheckStateIfNoKingPiece() {
        String layout = "c00bbbbb00c00000b0000000000000000b0000w0000bb000www000bbb0ww0ww0bbb000www000bb0000w0000b0000000000000000b00000c00bbbbb00c";
        int turnCount = 1;
        char turn = 'b';
		String aTime = "11:11:11";
		String dTime = "11:11:11";
        assertEquals(false, SaveAndLoad.checkState(layout,turn,turnCount, aTime, dTime));
    }

    // This test checks that the checkState function returns false when given an invalid layout with too many white
    // pieces.
    @Test
    public void testCheckStateIfTooManyWhitePieces() {
        String layout = "c00bbbbb00c00000b0000000000000000b0000w0000bb000www000bbb0wwkww0bbb000www000bw0000w0000b0000000000000000b00000c00bbbbb00c";
        int turnCount = 1;
        char turn = 'b';
		String aTime = "11:11:11";
		String dTime = "11:11:11";
        assertEquals(false, SaveAndLoad.checkState(layout,turn,turnCount, aTime, dTime));
    }

    // This test checks that the checkState function returns false when given an invalid layout with too many black
    // pieces.
    @Test
    public void testCheckStateIfTooManyBlackPieces() {
        String layout = "c00bbbbb00c00bbbb0000000000000000b0000w0000bb000www000bbb0wwkww0bbb000www000bb0000w0000b0000000000000000b00000c00bbbbb00c";
        int turnCount = 1;
        char turn = 'w';
		String aTime = "11:11:11";
		String dTime = "11:11:11";
        assertEquals(false, SaveAndLoad.checkState(layout,turn,turnCount, aTime, dTime));

    }

    // This test checks that the checkState function returns false when given an invalid layout with no black pieces.
    @Test
    public void testCheckStateIfNoBlackPieces() {
        String layout = "c000000000c000000000000000000000000000w000000000www0000000wwkww0000000www000000000w000000000000000000000000000c000000000c";
        int turnCount = 1;
        char turn = 'b';
		String aTime = "11:11:11";
		String dTime = "11:11:11";
        assertEquals(false, SaveAndLoad.checkState(layout,turn,turnCount, aTime, dTime));
    }

    // This test checks that the checkState function returns false when given an invalid layout with not enough corner
    // pieces.
    @Test
    public void testCheckStateIfNotEnoughCornerPieces() {
        String layout = "c00bbbbb00c00000b0000000000000000b0000w0000bb000www000bbb0wwkww0bbb000www000bb0000w0000b0000000000000000b00000c00bbbbb00";
        int turnCount = 1;
        char turn = 'w';
		String aTime = "11:11:11";
		String dTime = "11:11:11";
        assertEquals(false, SaveAndLoad.checkState(layout,turn,turnCount, aTime, dTime));
    }
	*/
}
import copenhagen.SaveAndLoad;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.File;

import copenhagen.Hnefatafl;

public class HnefataflTest {

    // This test checks if the saveGame function returns true when the save is successful.
    @Test
    public void testSaveGameIfSuccessful() {
        SaveAndLoad mockedSaveAndLoad = mock(SaveAndLoad.class);
        when(mockedSaveAndLoad.save(11, 'b', 1)).thenReturn(true);
        assertEquals(true, Hnefatafl.saveGame(mockedSaveAndLoad));
    }

    // This test checks if the saveGame function returns false when the save is unsuccessful.
    @Test
    public void testSaveGameIfUnsuccessful() {
        SaveAndLoad mockedSaveAndLoad = mock(SaveAndLoad.class);
        when(mockedSaveAndLoad.save(11, 'b', 1)).thenReturn(false);
        assertEquals(false, Hnefatafl.saveGame(mockedSaveAndLoad));
    }

    // This test checks if the loadGame function returns true when a game file is successfully loaded.
    @Test
    public void testLoadGameIfFileLoaded() {
        SaveAndLoad mockedSaveAndLoad = mock(SaveAndLoad.class);
        File mockedFile = mock(File.class);
        when(mockedSaveAndLoad.load()).thenReturn(mockedFile);
        assertEquals(true, Hnefatafl.loadGame(mockedSaveAndLoad));
    }

    // This test checks if the loadGame function returns false when a game file is unable to be loaded.
    @Test
    public void testLoadGameIfFileNotLoaded() {
        SaveAndLoad mockedSaveAndLoad = mock(SaveAndLoad.class);
        when(mockedSaveAndLoad.load()).thenReturn(null);
        assertEquals(false, Hnefatafl.loadGame(mockedSaveAndLoad));
    }

    // This test checks that the turn count for a new game is set to 1.
    @Test
    public void testNewGameResetTurns() {
        assertEquals(1, Hnefatafl.newGameResetTurns());
    }

    //test that attack color is set correctly and to lowercase
    @Test
    public void testSetAttackColor(){
        Hnefatafl.setAttackColor("Green");
        assertEquals("green",Hnefatafl.getAttackColor());
    }

    //test that defense color is set correctly and to lowercase
    @Test
    public void testSetDefenseColor(){
        Hnefatafl.setDefenseColor("Blue");
        assertEquals("blue",Hnefatafl.getDefenseColor());
    }

    //Test that the image link is formatted correctly
    @Test
    public void testSetDefenseColorSetsImage(){
        Hnefatafl.setDefenseColor("Orange");
        assertEquals("images/orangepiece.png",Hnefatafl.getDefendPieceAddr());
    }

    //Test that the selected image link is formatted correcly
    @Test
    public void testSetDefenseColorSetsSelectedImage(){
        Hnefatafl.setDefenseColor("Black");
        assertEquals("images/blackpieceSelected.png",Hnefatafl.getDefendSelAddr());
    }

    //Test that the image link is formatted correcly
    @Test
    public void testSetAttackColorSetsImage(){
        Hnefatafl.setAttackColor("Orange");
        assertEquals("images/orangepiece.png",Hnefatafl.getAttackPieceAddr());
    }

    //Test that the selected image link is formatted correcly
    @Test
    public void testSetAttackColorSetsSelectedImage(){
        Hnefatafl.setAttackColor("Black");
        assertEquals("images/blackpieceSelected.png",Hnefatafl.getAttackSelAddr());
    }
}

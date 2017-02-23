import org.junit.Test;
import static org.junit.Assert.*;

import copenhagen.SaveAndLoad;

public class SaveAndLoadTest {
        
        
	@Test
    public void testSave() {
        
    }
      
    @Test
    public void testLoad(){
            
    }
	
	@Test
    public void testCheckState(){
		String layout = "";
		int turnCount = 1;
		char turn = 'b';
		
		//check empty string layout. should be false
        assertEquals(SaveAndLoad.checkState(layout,turn,turnCount), false);
		
		layout = "c00bbbbb00c00000b0000000000000000b0000w0000bb000www000bbb0wwkww0bbb000www000bb0000w0000b0000000000000000b00000c00bbbbb00c";
		
		//check valid layout. should be true
		assertEquals(SaveAndLoad.checkState(layout,turn,turnCount), true);
		
		turnCount = -1;
		//check invalid turncount. should be false
		assertEquals(SaveAndLoad.checkState(layout,turn,turnCount), false);
		turnCount = 1;
		turn = 'k';
		
		//check invalid turn. should be false
		assertEquals(SaveAndLoad.checkState(layout,turn,turnCount), false);
		
		turn = 'w';
		
		layout = "c00bbbbb00c00bbbb0000000000000000b0000w0000bb000www000bbb0wwkww0bbb000www000bb0000w0000b0000000000000000b00000c00bbbbb00c";
		//check invalid layout. should be false
		assertEquals(SaveAndLoad.checkState(layout,turn,turnCount), false);
		
		layout = "c00bbbbb00c00000b0000000000000000b0000w0000bb000www000bbb0wwkww0bbb000www000bb0000w0000b0000000000000000b00000c00bbbbb00";
		//check invalid layout. should be false
		assertEquals(SaveAndLoad.checkState(layout,turn,turnCount), false);
		
		layout = "c00b0b0000c0000000000000000000000000b0bb000bbw00b0b000bbbw0wkb0wbb000bww00000bw000000b000000000w00000000b0b000c000bb0000c";
		turnCount = 25;
		
		//check valid layout. should be true
		assertEquals(SaveAndLoad.checkState(layout,turn,turnCount), true);
		
		
    }
}
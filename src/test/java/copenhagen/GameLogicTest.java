import copenhagen.GameLogic;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameLogicTest {

	// This test checks if a piece can move if the piece and turn are white.
	@Test
	public void testPieceCanMoveIfPieceAndTurnAreWhite() {
		assertEquals(true, GameLogic.pieceCanMove('w', 'w'));
	}

	// This test checks if a piece can move if the piece and turn are black.
	@Test
	public void testPieceCanMoveIfPieceAndTurnAreBlack() {
		assertEquals(true, GameLogic.pieceCanMove('b', 'b'));
	}

	// This test checks if a piece can move if the piece is the king and turn is white.
	@Test
	public void testPieceCanMoveIfPieceIsKingAndTurnIsWhite() {
		assertEquals(true, GameLogic.pieceCanMove('k', 'w'));
	}

	// This test checks if a piece can move if the piece is white and turn is black.
	@Test
	public void testPieceCanMoveIfPieceIsWhiteAndTurnIsBlack() {
		assertEquals(false, GameLogic.pieceCanMove('w', 'b'));
	}

	// This test checks if a piece can move if the piece is black and turn is white.
	@Test
	public void testPieceCanMoveIfPieceIsBlackAndTurnIsWhite() {
		assertEquals(false, GameLogic.pieceCanMove('b', 'w'));
	}

	// This test checks to make sure that correct piece is returned.
	@Test
	public void testGetPiece() {
		int col = 4;
		int row = 8;
		char expected = 'k';
		char[][] gameBoard = new char [11][11];
		gameBoard[col][row] = 'k';
		gameBoard[col-1][row] = gameBoard[col+1][row] = gameBoard[col][row-1] = gameBoard[col][row+1] = 'b';
		GameLogic gl = new GameLogic();
		gl.gameBoardArray = gameBoard;
		char actual = gl.getPiece(col, row);
		assertEquals(expected, actual);
	}

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
				assertEquals(expected[i][j], actual[i][j]);
			}
		}
	}

	// This test checks to see if the game board array is filled with 0s if the size of the board is not 11
	// or potentially 9 if that feature is added later.
	@Test
	public void testSetStartingPiecesIfInvalidSize() {
		int size = 1;

		char[][] expected = new char [size][size];
		expected[0][0] = '0';

		char[][] actual = GameLogic.setStartingPieces(size);
		assertEquals(expected[0][0], actual[0][0]);
	}

	// This test checks to make sure the game board array is sent back properly when called.
	@Test
	public void testGetGameBoardArray() {
		char[][] expected = new char [1][1];
		expected[0][0] = '0';
		GameLogic gl = new GameLogic();
		gl.gameBoardArray = expected;
		char[][] actual = gl.getGameBoardArray();
		assertEquals(expected[0][0], actual[0][0]);
	}

	// This test checks that the defenders win when the king reaches one of the corners.
	@Test
	public void testDefendersWinWhenKingInCorner() {
		char expected = 'w';
		char[][] board = new char[11][11];
		board[0][0] = 'k';
		GameLogic gl = new GameLogic();
		gl.gameBoardArray = board;
		char actual = gl.checkWinner();
		assertEquals(actual, expected);

	}

	@Test
	public void testAttackersWinWhenSurroundKing() {
		char expected = 'b';
		char[][] board = new char[11][11];
		GameLogic gl = new GameLogic();
		char actual;
		board[2][2] = 'k';
		board[2][1] = board[1][2] = board[3][2] = board[2][3] = 'b';
		gl.gameBoardArray = board;
		actual = gl.checkWinner();
		assertEquals(actual, expected);

	}
    @Test
    public void testAttackersWinWhenSurroundKingLeftOfThrone() {
        char expected = 'b';
        char[][] hBoard = new char[11][11];
        GameLogic gl = new GameLogic();
        
        hBoard[5][4] = 'k';
        hBoard[5][3] = hBoard[4][4] = hBoard[6][4] = 'b';
		gl.gameBoardArray = hBoard;
		char actual = gl.checkWinner();
        assertEquals(actual, expected);
    }
    
    @Test
    public void testAttackersWinWhenSurroundKingRightOfThrone() {
        char expected = 'b';
        char[][] hBoard = new char[11][11];
        GameLogic gl = new GameLogic();
        
        hBoard[5][6] = 'k';
        hBoard[5][7] = hBoard[4][6] = hBoard[6][6] = 'b';
		gl.gameBoardArray = hBoard;
		char actual = gl.checkWinner();
        assertEquals(actual, expected);
    }
    
    @Test
    public void testAttackersWinWhenSurroundKingBelowThrone() {
        char expected = 'b';
        char[][] hBoard = new char[11][11];
        GameLogic gl = new GameLogic();
        
        hBoard[6][5] = 'k';
        hBoard[7][5] = hBoard[6][6] = hBoard[6][4] = 'b';
		gl.gameBoardArray = hBoard;
		char actual = gl.checkWinner();
        assertEquals(actual, expected);
    }
    
    @Test
    public void testAttackersWinWhenSurroundKingAboveThrone() {
        char expected = 'b';
        char[][] hBoard = new char[11][11];
        GameLogic gl = new GameLogic();
        
        hBoard[4][5] = 'k';
        hBoard[3][5] = hBoard[4][4] = hBoard[4][6] = 'b';
		gl.gameBoardArray = hBoard;
		char actual = gl.checkWinner();
        assertEquals(actual, expected);
    }
    
    public void testAttackersWinWhenEntirelySurroundDefenders() {
        char expected = 'b';
        char[][] hBoard = new char[11][11];
        GameLogic gl = new GameLogic();
        
        hBoard[3][5] = hBoard[4][4] = hBoard[4][5] = hBoard[4][6] = hBoard[5][3] = hBoard[5][4] = 'w';
        hBoard[5][6] = hBoard[5][7] = hBoard[6][4] = hBoard[6][5] = hBoard[6][6] = hBoard[7][5] = 'w';

        hBoard[2][1] = hBoard[3][1] = hBoard[4][1] = hBoard[5][1] = hBoard[6][1] = hBoard[7][1] = hBoard[8][1] = hBoard[1][2] = hBoard[1][3] = hBoard[1][4] = hBoard[1][5] = hBoard[1][6] = hBoard[1][7] = hBoard[1][8] =hBoard[8][2] = hBoard[8][3] = hBoard[8][4] = hBoard[8][5] = hBoard[8][6] = hBoard[8][7] = hBoard[1][8] = hBoard[2][8] = hBoard[3][8] = hBoard[4][8]  = hBoard[5][8] = hBoard[6][8] = hBoard[7][8] = hBoard[8][8] = 'b';
        hBoard[5][5] = 'k';
		gl.gameBoardArray = hBoard;
		char actual = gl.checkWinner();
        assertEquals(actual, expected);
        
    }
    
    
    public void testAttackersDoNotWinWhenNotEntirelySurroundDefenders() {
        char expected = '0';
        char[][] hBoard = new char[11][11];
        GameLogic gl = new GameLogic();
        
        hBoard[3][5] = hBoard[4][4] = hBoard[4][5] = hBoard[4][6] = hBoard[5][3] = hBoard[5][4] = 'w';
        hBoard[5][6] = hBoard[5][7] = hBoard[6][4] = hBoard[6][5] = hBoard[6][6] = hBoard[7][5] = 'w';
        
        hBoard[2][1] = hBoard[3][1] = hBoard[4][1] = hBoard[5][1] = hBoard[6][1] = hBoard[7][1] = hBoard[8][1] = hBoard[1][2] = hBoard[1][3] = hBoard[1][4] = hBoard[1][5] = hBoard[1][6] = hBoard[1][7] = hBoard[1][8] =hBoard[8][2] = hBoard[8][3] = hBoard[8][4] = hBoard[8][5] = hBoard[8][6] = hBoard[8][7] = hBoard[1][8] = hBoard[2][8] = hBoard[3][8] = hBoard[4][8]  = hBoard[5][8] = hBoard[6][8] = hBoard[7][8] = hBoard[8][8] = 'b';
        hBoard[5][10] = 'k';
		gl.gameBoardArray = hBoard;
		char actual = gl.checkWinner();
        assertEquals(actual, expected);
    }
    
	@Test
	public void testMoveWhitePiece(){
		char[][] start = new char[11][11];
		start[1][1] = 'w';
		GameLogic.gameBoardArray = start;
		GameLogic.movePieceOnBoard(1,1,4,5);
		char[][] moved = GameLogic.getGameBoardArray();
		assertEquals(moved[1][1],'0');
		assertEquals(moved[4][5],'w');
	}

	@Test
	public void testMoveBlackPiece(){
		char[][] start = new char[11][11];
		start[8][8] = 'b';
		GameLogic.gameBoardArray = start;
		GameLogic.movePieceOnBoard(8,8,5,1);
		char[][] moved = GameLogic.getGameBoardArray();
		assertEquals(moved[8][8],'0');
		assertEquals(moved[5][1],'b');
	}

	@Test
	public void testMoveKingPiece(){
		char[][] start = new char[11][11];
		start[6][8] = 'k';
		GameLogic.gameBoardArray = start;
		GameLogic.movePieceOnBoard(6,8,3,9);
		char[][] moved = GameLogic.getGameBoardArray();
		assertEquals(moved[6][8],'0');
		assertEquals(moved[3][9],'k');
	}

	@Test
	public void testMoveFromCenter(){
		char[][] start = new char[11][11];
		start[5][5] = 'k';
		GameLogic.gameBoardArray = start;
		GameLogic.movePieceOnBoard(5,5,4,4);
		char[][] moved = GameLogic.getGameBoardArray();
		assertEquals(moved[5][5],'c');
		assertEquals(moved[4][4],'k');
	}

	@Test
	public void testMoveFromCorner(){
		char[][] start = new char[11][11];
		start[0][0] = 'w';
		GameLogic.gameBoardArray = start;
		GameLogic.movePieceOnBoard(0,0,7,3);
		char[][] moved = GameLogic.getGameBoardArray();
		assertEquals(moved[0][0],'c');
		assertEquals(moved[7][3],'w');
	}
}

package copenhagen;

import java.util.LinkedList;

/**
 * This class contains all the logic for the game board. The game board is represented by a two dimensional
 * character array. The location in the array is the location on the board. The first dimension represents the row and
 * the second dimension represents the column.
 * The char value represents the piece type:
 * '0' for empty
 * 'c' for the throne and the corner squares (the five restricted squares)
 * 'w' for a white piece
 * 'b' for a black piece
 * 'k' for the king piece
 */
public class GameLogic{
    private static int GRID_SIZE = 11;
    public static char[][] gameBoardArray;

    /**
     * This function checks whether a piece is allowed to be currently moved.
     * @param piece This is piece that is trying to be moved.
     * @return This function will return true if the piece can be moved, else false if it can not be moved.
     */
    public static boolean pieceCanMove(char piece, char turn) {
        if (piece == turn || (piece == 'k' && turn == 'w')) {
            return true;
        } else {
            return false;
        }
    }

    //TODO Add test
    /**
     * This function updates the game board.
     * @param col This parameter is the column that will be updated.
     * @param row This parameter is the row that will be updated.
     * @param piece This parameter is the piece that will be placed in the given column and row of the game board.
     */
    public static void updateGameBoard(int col, int row, char piece) {
        gameBoardArray[col][row] = piece;
    }

    /**
     * This function gets what type piece is at a given location.
     * @param col This parameter is the column in the game board.
     * @param row This parameter is the row in the game board.
     * @return This will return the character that represents the piece.
     */
    public static char getPiece(int col, int row) {
        return gameBoardArray[col][row];
    }

    /**
     * This function removes all the pieces that were captured on the board by the move just completed.
     * @param col This parameter is the column associated with a piece that is about to be removed.
     * @param row This parameter is the row associated with a piece that is about to be removed.
     */
	public static void removeCapturedPieces(LinkedList<Integer> col, LinkedList<Integer> row) {
        GameBoard hBoard = Hnefatafl.getHBoard();
        for (int i = 0; i < col.size(); i++) {
            int c = col.get(i);
            int r = row.get(i);
            if (getPiece(c,r) == 'k') {
                // Sandwiching a non-king piece captures it
                // But the king is only captured if it is surrounded on all 4 (or 3 if the king is on an edge) sides
                return;
            }
            gameBoardArray[c][r] = '0';
            GameBoard.removeCapturedPiecesUI(c,r);
        }
    }

    /**
     * This function sets the starting location of each game piece on the board.
     * @param size This parameter is the size of the game board.
     * @return This returns a two dimensional character array that represents the game board with game pieces at their
     * starting positions.
     */
    public static char[][] setStartingPieces(int size) {
        GRID_SIZE = size;
        char[][] s = new char [GRID_SIZE][GRID_SIZE];
        //Initialize to null char
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                s[i][j] = '0';
            }
        }

        if (GRID_SIZE==11) {
            s[0][3] = s[0][4] = s[0][5] = s[0][6] = s[0][7] = s[1][5] = 'b';
            s[3][0] = s[4][0] = s[5][0] = s[6][0] = s[7][0] = s[5][1] = 'b';
            s[10][3] = s[10][4] = s[10][5] = s[10][6] = s[10][7] = s[9][5] = 'b';
            s[3][10] = s[4][10] = s[5][10] = s[6][10] = s[7][10] = s[5][9] = 'b';

            s[3][5] = s[4][4] = s[4][5] = s[4][6] = s[5][3] = s[5][4] = 'w';
            s[5][6] = s[5][7] = s[6][4] = s[6][5] = s[6][6] = s[7][5] = 'w';
            s[0][0] = s[0][10] = s[10][0] = s[10][10] = s[5][5] = 'c';
            s[5][5] = 'k';
        }
        gameBoardArray = s;
        return s;
    }

    /**
     * This function gets the game board containing the location of every game piece.
     * @return This returns a two dimensional character array representing the game pieces and their location on the
     * game board.
     */
    public static char[][] getGameBoardArray() {
        return gameBoardArray;
    }

	/**
	 * This function finds where a piece is allowed to move based on the rules of the game.
	 * @param piece This parameter is the current game piece that is being looked at.
	 * @param col This parameter is the current column that the game piece is located at.
	 * @param row This parameter is the current row that the game piece is located at.
	 * @return This function returns a boolean array matching the gameboard with true values on all of the spaces a
	 * piece can move to.
	 */
	public static boolean[][] getValidMoves(char piece, int col, int row, char[][] gameBoard){
		boolean[][] validSpaces = new boolean[GRID_SIZE][GRID_SIZE];
		for(int i=col+1; i<GRID_SIZE; i++){//check move right
			if((gameBoard[i][row] == '0') || (piece == 'k' && gameBoard[i][row] == 'c')){
				validSpaces[i][row] = true;
			} else if (piece != 'k' && gameBoard[i][row] == 'c'){
				validSpaces[i][row] = false;
			} else {
				break;
			}
		}
		for(int i=col-1; i>=0; i--){//check move left
			if((gameBoard[i][row] == '0') || (piece == 'k' && gameBoard[i][row] == 'c')){
				validSpaces[i][row] = true;
			}else if (piece != 'k' && gameBoard[i][row] == 'c'){
				validSpaces[i][row] = false;
			}else{
				break;
			}
		}
		for(int i=row+1; i<GRID_SIZE; i++){//check move down
			if((gameBoard[col][i] == '0') || (piece == 'k' && gameBoard[col][i] == 'c')){
				validSpaces[col][i] = true;
			}else if (piece != 'k' && gameBoard[col][i] == 'c'){
				validSpaces[col][i] = false;
			}else{
				break;
			}
		}
		for(int i=row-1; i>=0; i--){//check move up
			if((gameBoard[col][i] == '0') || (piece == 'k' && gameBoard[col][i] == 'c')){
				validSpaces[col][i] = true;
			}else if (piece != 'k' && gameBoard[col][i] == 'c'){
			 	validSpaces[col][i] = false;
			}else{
				break;
			}
		}
		return validSpaces;
	}

	/**
	 This function checks to see if there is a winner at the end of each turn
	 */
	public static char checkWinner(char[][] gameBoard) {
        boolean defendersSurrounded = false;
        boolean foundExit = false;
        // Check if king is entirely surrounded
		for (int i = 1; i < gameBoard.length-1; i++) {
			for (int j = 1; j < gameBoard.length-1; j++) {
				if(gameBoard[i][j] == 'k') { // Found the king piece
					if (gameBoard[i+1][j] == 'b' && gameBoard[i][j+1] == 'b' && gameBoard[i-1][j] == 'b' && gameBoard[i][j-1] == 'b' ) {
						// King is entirely surrounded so attackers win
						return 'b';
					}
				}
			}
		}
		// Check Corners for Defenders Win
		if (gameBoard[0][0] == 'k' || gameBoard[0][10] == 'k' || gameBoard[10][0] == 'k' || gameBoard[10][10] == 'k') {
			// King has reached one of the corners so defenders win
			return 'w';
		}
        // Check Left of Throne for Attackers Win
        if(gameBoard[5][4] == 'k') {
            if (gameBoard[5][3] == 'b' && gameBoard[4][4] == 'b' && gameBoard[6][4] == 'b') {
                // King is surrounded to the left of the throne
                return 'b';
            }
        }
        // Check Right of Throne for Attackers Win
        if(gameBoard[5][6] == 'k') {
            if (gameBoard[5][7] == 'b' && gameBoard[4][6] == 'b' && gameBoard[6][6] == 'b') {
                // King is surrounded to the right of the throne
                return 'b';
            }
        }
        // Check Below Throne for Attackers Win
        if(gameBoard[6][5] == 'k') {
            if (gameBoard[7][5] == 'b' && gameBoard[6][4] == 'b' && gameBoard[6][6] == 'b') {
                // King is surrounded below the throne
                return 'b';
            }
        }
        // Check Above Throne for Attackers Win
        if(gameBoard[4][5] == 'k') {
            if (gameBoard[3][5] == 'b' && gameBoard[4][4] == 'b' && gameBoard[4][6] == 'b') {
                // King is surrounded above the throne
                return 'b';
            }
        }
        
        // Check if Attackers have entirely surrounded Defenders
        for (int i = 0; i < gameBoard.length; i++) {
             for (int j = 0; j < gameBoard.length; j++) {
                 if(gameBoard[i][j] == 'b') {
                     // Look for another Attacker blocking the Defenders in this collumn
                     for (int n = 0; n < gameBoard.length; n++) {
                         if (gameBoard[n][j] == 'w' || gameBoard[n][j] == 'k')
                             defendersSurrounded = false;
                         else if (gameBoard[n][j] == 'b')
                             defendersSurrounded = true;
                         if (defendersSurrounded == false && n == gameBoard.length - 1) {
                             return '0';
                         }
                     }
                     // Look for another Attacker blocking the Defenders in this row
                     for (int n = 0; n < gameBoard.length; n++) {
                         if (gameBoard[i][n] == 'w' || gameBoard[i][n] == 'k')
                             defendersSurrounded = false;
                         else if (gameBoard[n][j] == 'b')
                             defendersSurrounded = true;
                         if (defendersSurrounded == false && n == gameBoard.length - 1){
                             return '0';
                         }
                     }
                 }
             }
        }
        // Defenders are entirely surrounded so Attackers win!
        if (!foundExit)
            return 'b';
        
        // There is not a winner yet so continue playing
		return '0';
	}

    /**
     * This is the function that does the actual work from moving a game piece from its original starting row and column
     * to its new row and column.
     * @param startCol This parameter is the starting column of the game piece.
     * @param startRow This parameter is the starting row of the game piece.
     * @param destCol This parameter is the destination column of the game piece.
     * @param destRow This parameter is the destination column of the game piece.
     */
	public static void movePieceOnBoard(int startCol,int startRow,int destCol, int destRow){
		//update gameboard array
		char pieceType = GameLogic.getPiece(startCol, startRow);
		if ((startCol==0 && startRow==0) ||
			(startCol==GRID_SIZE-1 && startRow==GRID_SIZE-1) ||
			(startCol==0 && startRow==GRID_SIZE-1) ||
			(startCol==GRID_SIZE-1 && startRow==0) || (startCol==5 && startRow==5)) {
			Hnefatafl.pieceLayout[startCol][startRow] = 'c';
			GameLogic.updateGameBoard(startCol, startRow, 'c');

		}else{
			Hnefatafl.pieceLayout[startCol][startRow] = '0';
            GameLogic.updateGameBoard(startCol, startRow, '0');
        }
		Hnefatafl.pieceLayout[destCol][destRow] = pieceType;
        GameLogic.updateGameBoard(destCol, destRow, pieceType);
        Hnefatafl.findCapturedPieces(pieceType, destCol, destRow);
	}
}

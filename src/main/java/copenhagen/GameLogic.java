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
	public static boolean[][] getValidMoves(char piece, int col, int row){
		boolean[][] validSpaces = new boolean[GRID_SIZE][GRID_SIZE];
		for(int i=col+1; i<GRID_SIZE; i++){//check move right
			if((gameBoardArray[i][row] == '0') || (piece == 'k' && gameBoardArray[i][row] == 'c')){
				validSpaces[i][row] = true;
			} else if (piece != 'k' && gameBoardArray[i][row] == 'c'){
				validSpaces[i][row] = false;
			} else {
				break;
			}
		}
		for(int i=col-1; i>=0; i--){//check move left
			if((gameBoardArray[i][row] == '0') || (piece == 'k' && gameBoardArray[i][row] == 'c')){
				validSpaces[i][row] = true;
			}else if (piece != 'k' && gameBoardArray[i][row] == 'c'){
				validSpaces[i][row] = false;
			}else{
				break;
			}
		}
		for(int i=row+1; i<GRID_SIZE; i++){//check move down
			if((gameBoardArray[col][i] == '0') || (piece == 'k' && gameBoardArray[col][i] == 'c')){
				validSpaces[col][i] = true;
			}else if (piece != 'k' && gameBoardArray[col][i] == 'c'){
				validSpaces[col][i] = false;
			}else{
				break;
			}
		}
		for(int i=row-1; i>=0; i--){//check move up
			if((gameBoardArray[col][i] == '0') || (piece == 'k' && gameBoardArray[col][i] == 'c')){
				validSpaces[col][i] = true;
			}else if (piece != 'k' && gameBoardArray[col][i] == 'c'){
			 	validSpaces[col][i] = false;
			}else{
				break;
			}
		}
		return validSpaces;
	}

	/**
	 * This function checks to see if there is a winner at the end of each turn.
	 */
	public static char checkWinner() {
        //boolean defendersSurrounded = false;
        //boolean foundExit = false;
        
        // Check if king is entirely surrounded
		for (int i = 1; i < gameBoardArray.length-1; i++) {
			for (int j = 1; j < gameBoardArray.length-1; j++) {
				if(gameBoardArray[i][j] == 'k') { // Found the king piece
					if (gameBoardArray[i+1][j] == 'b' && gameBoardArray[i][j+1] == 'b' && gameBoardArray[i-1][j] == 'b' && gameBoardArray[i][j-1] == 'b' ) {
						// King is entirely surrounded so attackers win
						return 'b';
					}
				}
			}
		}
		// Check Corners for Defenders Win
		if (gameBoardArray[0][0] == 'k' || gameBoardArray[0][10] == 'k' || gameBoardArray[10][0] == 'k' || gameBoardArray[10][10] == 'k') {
			// King has reached one of the corners so defenders win
			return 'w';
		}
        // Check Left of Throne for Attackers Win
        if(gameBoardArray[5][4] == 'k') {
            if (gameBoardArray[5][3] == 'b' && gameBoardArray[4][4] == 'b' && gameBoardArray[6][4] == 'b') {
                // King is surrounded to the left of the throne
                return 'b';
            }
        }
        // Check Right of Throne for Attackers Win
        if(gameBoardArray[5][6] == 'k') {
            if (gameBoardArray[5][7] == 'b' && gameBoardArray[4][6] == 'b' && gameBoardArray[6][6] == 'b') {
                // King is surrounded to the right of the throne
                return 'b';
            }
        }
        // Check Below Throne for Attackers Win
        if(gameBoardArray[6][5] == 'k') {
            if (gameBoardArray[7][5] == 'b' && gameBoardArray[6][4] == 'b' && gameBoardArray[6][6] == 'b') {
                // King is surrounded below the throne
                return 'b';
            }
        }
        // Check Above Throne for Attackers Win
        if(gameBoardArray[4][5] == 'k') {
            if (gameBoardArray[3][5] == 'b' && gameBoardArray[4][4] == 'b' && gameBoardArray[4][6] == 'b') {
                // King is surrounded above the throne
                return 'b';
            }
        }
        
        /**
         Doesn't work correctly
        
        // Check if Attackers have entirely surrounded Defenders
        for (int i = 0; i < gameBoardArray.length; i++) {
             for (int j = 0; j < gameBoardArray.length; j++) {
                 if(gameBoardArray[i][j] == 'b') {
                     // Look for another Attacker blocking the Defenders in this column
                     for (int n = 0; n < gameBoardArray.length; n++) {
                         if (gameBoardArray[n][j] == 'w' || gameBoardArray[n][j] == 'k')
                             defendersSurrounded = false;
                         else if (gameBoardArray[n][j] == 'b')
                             defendersSurrounded = true;
                         if (defendersSurrounded == false && n == gameBoardArray.length - 1) {
                             return '0';
                         }
                     }
                     // Look for another Attacker blocking the Defenders in this row
                     for (int n = 0; n < gameBoardArray.length; n++) {
                         if (gameBoardArray[i][n] == 'w' || gameBoardArray[i][n] == 'k')
                             defendersSurrounded = false;
                         else if (gameBoardArray[n][j] == 'b')
                             defendersSurrounded = true;
                         if (defendersSurrounded == false && n == gameBoardArray.length - 1){
                             return '0';
                         }
                     }
                 }
             }
        }
        // Defenders are entirely surrounded so Attackers win!
        if (!foundExit)
            return 'b';
        */
        
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
			updateGameBoard(startCol, startRow, 'c');

		}else{
            updateGameBoard(startCol, startRow, '0');
        }
        updateGameBoard(destCol, destRow, pieceType);
        findCapturedPieces(pieceType, destCol, destRow);
	}
	
	/**
     * This function is called to determine if there is a shieldwall during a move by a piece.
     * TODO: Refactor this code!
     * @param piece This is the piece that has been moved.
     * @param col This is the column of where the piece will be going.
     * @param row This is the row of where the piece will be going.
     * @param capturePiece This is the kind of piece that is allowed to be captured.
     * @param helperPiece This is the kind of piece that helps in a capture:
     *                     i.e. another black piece if piece == 'b'
     *                          the king piece if piece == 'w'
     *                          a white piece if piece == 'k'
     */
    private static void findShieldwall(char piece, int col, int row, char capturePiece, char helperPiece) {
        LinkedList<Integer> capturedPieceCol = new LinkedList<>();
        LinkedList<Integer> capturedPieceRow = new LinkedList<>();
        int counter = 0;

        if (col == 0) {
            if (row <= 7) {
                for (int i = row+1; i < 11; i++) {
                    if (gameBoardArray[col][i] == '0') {
                        break;
                    }
                    else if (gameBoardArray[col][i] == capturePiece && (gameBoardArray[col+1][i] == piece || gameBoardArray[col+1][i] == helperPiece)) {
                        capturedPieceCol.add(col);
                        capturedPieceRow.add(i);
                        counter++;
                    }
                    else if (gameBoardArray[col][i] == piece || gameBoardArray[col][i] == helperPiece || gameBoardArray[col][i] == 'c') {
                        if (counter >= 2) {
                            removeCapturedPieces(capturedPieceCol, capturedPieceRow);
                        }
                        break;
                    }
                }
            }
            if (row >= 3) {
                for (int i = row-1; i >= 0; i--) {
                    if (gameBoardArray[col][i] == '0') {
                        break;
                    }
                    else if (gameBoardArray[col][i] == capturePiece && (gameBoardArray[col+1][i] == piece || gameBoardArray[col+1][i] == helperPiece)) {
                        capturedPieceCol.add(col);
                        capturedPieceRow.add(i);
                        counter++;
                    }
                    else if (gameBoardArray[col][i] == piece || gameBoardArray[col][i] == helperPiece || gameBoardArray[col][i] == 'c') {
                        if (counter >= 2) {
                            removeCapturedPieces(capturedPieceCol, capturedPieceRow);
                        }
                        break;
                    }
                }
            }
        }

        if (col == GRID_SIZE-1) {
            if (row <= 7) {
                for (int i = row+1; i < 11; i++) {
                    if (gameBoardArray[col][i] == '0') {
                        break;
                    }
                    else if (gameBoardArray[col][i] == capturePiece && (gameBoardArray[col-1][i] == piece || gameBoardArray[col-1][i] == helperPiece)) {
                        capturedPieceCol.add(col);
                        capturedPieceRow.add(i);
                        counter++;
                    }
                    else if (gameBoardArray[col][i] == piece || gameBoardArray[col][i] == helperPiece || gameBoardArray[col][i] == 'c') {
                        if (counter >= 2) {
                            removeCapturedPieces(capturedPieceCol, capturedPieceRow);
                        }
                        break;
                    }
                }
            }
            if (row >= 3) {
                for (int i = row-1; i >= 0; i--) {
                    if (gameBoardArray[col][i] == '0') {
                        break;
                    }
                    else if (gameBoardArray[col][i] == capturePiece && (gameBoardArray[col-1][i] == piece || gameBoardArray[col-1][i] == helperPiece)) {
                        capturedPieceCol.add(col);
                        capturedPieceRow.add(i);
                        counter++;
                    }
                    else if (gameBoardArray[col][i] == piece || gameBoardArray[col][i] == helperPiece || gameBoardArray[col][i] == 'c') {
                        if (counter >= 2) {
                            removeCapturedPieces(capturedPieceCol, capturedPieceRow);
                        }
                        break;
                    }
                }
            }
        }

        if (row == 0) {
            if (col <= 7) {
                for (int i = col+1; i < 11; i++) {
                    if (gameBoardArray[i][row] == '0') {
                        break;
                    }
                    else if (gameBoardArray[i][row] == capturePiece && (gameBoardArray[i][row+1] == piece || gameBoardArray[i][row+1] == helperPiece)) {
                        capturedPieceCol.add(i);
                        capturedPieceRow.add(row);
                        counter++;
                    }
                    else if (gameBoardArray[i][row] == piece || gameBoardArray[i][row] == helperPiece || gameBoardArray[i][row] == 'c') {
                        if (counter >= 2) {
                            removeCapturedPieces(capturedPieceCol, capturedPieceRow);
                        }
                        break;
                    }
                }
            }
            if (col >= 3) {
                for (int i = col-1; i >= 0; i--) {
                    if (gameBoardArray[i][row] == '0') {
                        break;
                    }
                    else if (gameBoardArray[i][row] == capturePiece && (gameBoardArray[i][row+1] == piece || gameBoardArray[i][row+1] == helperPiece)) {
                        capturedPieceCol.add(i);
                        capturedPieceRow.add(row);
                        counter++;
                    }
                    else if (gameBoardArray[i][row] == piece || gameBoardArray[i][row] == helperPiece || gameBoardArray[i][row] == 'c') {
                        if (counter >= 2) {
                            removeCapturedPieces(capturedPieceCol, capturedPieceRow);
                        }
                        break;
                    }
                }
            }
        }

        if (row == GRID_SIZE-1) {
            if (col <= 7) {
                for (int i = col+1; i < 11; i++) {
                    if (gameBoardArray[i][row] == '0') {
                        break;
                    }
                    else if (gameBoardArray[i][row] == capturePiece && (gameBoardArray[i][row-1] == piece || gameBoardArray[i][row-1] == helperPiece)) {
                        capturedPieceCol.add(i);
                        capturedPieceRow.add(row);
                        counter++;
                    }
                    else if (gameBoardArray[i][row] == piece || gameBoardArray[i][row] == helperPiece || gameBoardArray[i][row] == 'c') {
                        if (counter >= 2) {
                            removeCapturedPieces(capturedPieceCol, capturedPieceRow);
                        }
                        break;
                    }
                }
            }
            if (col >= 3) {
                for (int i = col-1; i >= 0; i--) {
                    if (gameBoardArray[i][row] == '0') {
                        break;
                    }
                    else if (gameBoardArray[i][row] == capturePiece && (gameBoardArray[i][row-1] == piece || gameBoardArray[i][row-1] == helperPiece)) {
                        capturedPieceCol.add(i);
                        capturedPieceRow.add(row);
                        counter++;
                    }
                    else if (gameBoardArray[i][row] == piece || gameBoardArray[i][row] == helperPiece || gameBoardArray[i][row] == 'c') {
                        if (counter >= 2) {
                            removeCapturedPieces(capturedPieceCol, capturedPieceRow);
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * This function finds all the pieces that will be captured on the board by the move just completed.
     * @param piece This parameter is the piece that is getting moved.
     * @param col This parameter is the column that the piece will be in after it is moved.
     * @param row This parameter is the row that the piece will be in after it is moved.
     */
    public static void findCapturedPieces(char piece, int col, int row) {
	    char capturablePiece;
	    char kingPiece = 'b';
	    char helperPiece;
	    LinkedList<Integer> capturedPieceCol = new LinkedList<>();
        LinkedList<Integer> capturedPieceRow = new LinkedList<>();
	    if (piece == 'b') {
	        capturablePiece = 'w';
            kingPiece = 'k';
            helperPiece = 'b';
        }
        else if (piece == 'w'){
            capturablePiece = 'b';
            helperPiece = 'k';
        }
        else {
            capturablePiece = 'b';
            helperPiece = 'w';
        }
        if (col-2 >= 0) {
            if ((gameBoardArray[col - 1][row] == capturablePiece || gameBoardArray[col - 1][row] == kingPiece) && (gameBoardArray[col - 2][row] == piece || gameBoardArray[col - 2][row] == helperPiece || gameBoardArray[col - 2][row] == 'c')) {
                capturedPieceCol.add(col - 1);
                capturedPieceRow.add(row);
            }
        }
        if (col+2 <= GRID_SIZE-1) {
            if ((gameBoardArray[col + 1][row] == capturablePiece || gameBoardArray[col + 1][row] == kingPiece) && (gameBoardArray[col + 2][row] == piece || gameBoardArray[col + 2][row] == helperPiece || gameBoardArray[col + 2][row] == 'c')) {
                capturedPieceCol.add(col + 1);
                capturedPieceRow.add(row);
            }
        }
        if (row-2 >= 0) {
            if ((gameBoardArray[col][row - 1] == capturablePiece || gameBoardArray[col][row - 1] == kingPiece) && (gameBoardArray[col][row - 2] == piece || gameBoardArray[col][row - 2] == helperPiece || gameBoardArray[col][row - 2] == 'c')) {
                capturedPieceCol.add(col);
                capturedPieceRow.add(row - 1);
            }
        }
        if (row+2 <= GRID_SIZE-1) {
            if ((gameBoardArray[col][row + 1] == capturablePiece || gameBoardArray[col][row + 1] == kingPiece) && (gameBoardArray[col][row + 2] == piece || gameBoardArray[col][row + 2] == helperPiece || gameBoardArray[col][row + 2] == 'c')) {
                capturedPieceCol.add(col);
                capturedPieceRow.add(row + 1);
            }
        }
        if (!capturedPieceCol.isEmpty()) {
            removeCapturedPieces(capturedPieceCol, capturedPieceRow);
	    }
	    else {
            if (col == 0 || col == 10 || row == 0 || row == 10) {
                findShieldwall(piece, col, row, capturablePiece, helperPiece);
            }
        }
    }
}

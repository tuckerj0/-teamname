package copenhagen;

import java.util.LinkedList;

/**
 * This class contains all the logic for the game board. The game board is represented by a two dimensional
 * character array. The location in the array is the location on the board. The first dimension represents the row and
 * the second dimension represents the column.
 * The char value represents the piece type:
 * '0' for empty
 * 'c' for the throne and the corner squares (the five restricted squares)
 * 'w' for a defending piece
 * 'b' for an attacking piece
 * 'k' for the king piece
 */
public class GameLogic{
    private static int GRID_SIZE = 11;
    public static char[][] gameBoardArray;
    private static char attackers = 'b';
	private static char defenders = 'w';
	private static char king = 'k';
	private static char empty = '0';
	private static char restricted = 'c';
	private static int numOfAttackersLeft;
	private static int numOfDefendersLeft;

    /**
     * This function checks whether a piece is allowed to be currently moved.
     * @param piece This is piece that is trying to be moved.
     * @param turn This is which side's turn it currently is.
     * @return This function will return true if the piece can be moved, else false if it can not be moved.
     */
    public static boolean pieceCanMove(char piece, char turn) {
        if (piece == turn || (piece == king && turn == defenders)) {
            return true;
        } else {
            return false;
        }
    }

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
     * This function gets the number of attackers left on the board.
     * @return This returns the number of attackers left as an integer.
     */
    public static int getNumOfAttackersLeft() {
    	return numOfAttackersLeft;
	}

    /**
     * This function gets the number of defenders left on the board.
     * @return This returns the number of defenders left as an integer.
     */
	public static int getNumOfDefendersLeft() {
		return numOfDefendersLeft;
	}

    /**
     * This function sets the number of attackers left on the board.
     * @param i This parameter is the integer representing the number of attackers left.
     */
	public static void setNumOfAttackersLeft(int i) {
        numOfAttackersLeft = i;
    }

    /**
     * This function sets the number of defenders left on the board.
     * @param i This parameter is the integer representing the number of defenders left.
     */
    public static void setNumOfDefendersLeft(int i) {
        numOfDefendersLeft = i;
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
            if (getPiece(c,r) == king) {
                // Sandwiching a non-king piece captures it
                // But the king is only captured if it is surrounded on all 4 (or 3 if the king is on an edge) sides
                return;
            }
            if (gameBoardArray[c][r] == attackers) {
                numOfAttackersLeft--;
            }
            else {
                numOfDefendersLeft--;
            }
            gameBoardArray[c][r] = empty;
            GameBoard.removeCapturedPiecesUI(c,r);
        }
        BottomBar.updateNumOfPiecesLeft();
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
                s[i][j] = empty;
            }
        }

        if (GRID_SIZE==11) {
            s[0][3] = s[0][4] = s[0][5] = s[0][6] = s[0][7] = s[1][5] = attackers;
            s[3][0] = s[4][0] = s[5][0] = s[6][0] = s[7][0] = s[5][1] = attackers;
            s[10][3] = s[10][4] = s[10][5] = s[10][6] = s[10][7] = s[9][5] = attackers;
            s[3][10] = s[4][10] = s[5][10] = s[6][10] = s[7][10] = s[5][9] = attackers;

            s[3][5] = s[4][4] = s[4][5] = s[4][6] = s[5][3] = s[5][4] = defenders;
            s[5][6] = s[5][7] = s[6][4] = s[6][5] = s[6][6] = s[7][5] = defenders;
            s[0][0] = s[0][10] = s[10][0] = s[10][10] = s[5][5] = restricted;
            s[5][5] = king;
        }
        numOfAttackersLeft = 24;
        numOfDefendersLeft = 12;
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
			if((gameBoardArray[i][row] == empty) || (piece == king && gameBoardArray[i][row] == restricted)){
				validSpaces[i][row] = true;
			} else if (piece != king && gameBoardArray[i][row] == restricted){
				validSpaces[i][row] = false;
			} else {
				break;
			}
		}
		for(int i=col-1; i>=0; i--){//check move left
			if((gameBoardArray[i][row] == empty) || (piece == king && gameBoardArray[i][row] == restricted)){
				validSpaces[i][row] = true;
			}else if (piece != king && gameBoardArray[i][row] == restricted){
				validSpaces[i][row] = false;
			}else{
				break;
			}
		}
		for(int i=row+1; i<GRID_SIZE; i++){//check move down
			if((gameBoardArray[col][i] == empty) || (piece == king && gameBoardArray[col][i] == restricted)){
				validSpaces[col][i] = true;
			}else if (piece != king && gameBoardArray[col][i] == restricted){
				validSpaces[col][i] = false;
			}else{
				break;
			}
		}
		for(int i=row-1; i>=0; i--){//check move up
			if((gameBoardArray[col][i] == empty) || (piece == king && gameBoardArray[col][i] == restricted)){
				validSpaces[col][i] = true;
			}else if (piece != king && gameBoardArray[col][i] == restricted){
			 	validSpaces[col][i] = false;
			}else{
				break;
			}
		}
		return validSpaces;
	}

    /**
     * This function checks to see if there is a winner at the end of each turn.
     * @param turn This parameter is whose turn it currently is indicating which side to check if they won the game.
     * @return This function returns the results of which side won if anyone did. ('b' or 'w'; otherwise '0' if no
     * winner)
     */
	public static char checkWinner(char turn) {
		if (turn == attackers)
			return checkAttackWin();
		else
			return checkDefendWin();
	}

    /**
     * This function checks if the attacking team has won.
     * @return This returns a character 'b' representing if the attackers have won. Otherwise, returns '0'.
     */
	public static char checkAttackWin(){
		// Check Left of Throne for Attackers Win
        if(gameBoardArray[5][4] == king) {
            if (gameBoardArray[5][3] == attackers && gameBoardArray[4][4] == attackers && gameBoardArray[6][4] == attackers) {
                // King is surrounded to the left of the throne
                return attackers;
            }
        }
        // Check Right of Throne for Attackers Win
        if(gameBoardArray[5][6] == king) {
            if (gameBoardArray[5][7] == attackers && gameBoardArray[4][6] == attackers && gameBoardArray[6][6] == attackers) {
                // King is surrounded to the right of the throne
                return attackers;
            }
        }
        // Check Below Throne for Attackers Win
        if(gameBoardArray[6][5] == king) {
            if (gameBoardArray[7][5] == attackers && gameBoardArray[6][4] == attackers && gameBoardArray[6][6] == attackers) {
                // King is surrounded below the throne
                return attackers;
            }
        }
        // Check Above Throne for Attackers Win
        if(gameBoardArray[4][5] == king) {
            if (gameBoardArray[3][5] == attackers && gameBoardArray[4][4] == attackers && gameBoardArray[4][6] == attackers) {
                // King is surrounded above the throne
                return attackers;
            }
        }
		for (int i = 1; i < gameBoardArray.length-1; i++) {
			for (int j = 1; j < gameBoardArray.length-1; j++) {
				if(gameBoardArray[i][j] == king) { // Found the king piece
					if (gameBoardArray[i+1][j] == attackers && gameBoardArray[i][j+1] == attackers && gameBoardArray[i-1][j] == attackers && gameBoardArray[i][j-1] == attackers ) {
						// King is entirely surrounded so attackers win
						return attackers;
					}
				}
			}
		}
		if(checkEncircled()){
			return attackers;
		}
		return empty;
	}

    /**
     * This function checks if the defending team has won.
     * @return This returns a character 'w' representing if the defenders have won. Otherwise, returns '0'.
     */
	public static char checkDefendWin(){
		// Check Corners for Defenders Win
		if (gameBoardArray[0][0] == king || gameBoardArray[0][10] == king || gameBoardArray[10][0] == king || gameBoardArray[10][10] == king) {
			// King has reached one of the corners so defenders win
			return defenders;
		}
		return empty;
	}

	//*The follow variables and methods are helpers to find encirclement
	//*START
	
	private static char[][] visited = new char[GRID_SIZE][GRID_SIZE];
	private static int e;  //Flag

    /**
     * This function checks if the defending team is encircled.
     * @return This returns true if they are encircled. Otherwise, false.
     */
	public static boolean checkEncircled(){
		setGrid();
		e = 0;
		for(int i = 0; i < GRID_SIZE; i++){
			for(int j = 0; j < GRID_SIZE; j++){
				if((visited[i][j] == '0' || visited[i][j] == 't') && (gameBoardArray[i][j] == defenders)){
					boolean escaped = escape(i, j);
					if(escaped){
						e = 1;
						return false;
					}
				}
			}
		}
		if(e == 0){
			return true;
		}
		else{
			return false;
		}
	}


	/**
	 * This function simulates the escape of a piece. It is a helper method to checkEncircled.
	 * @param i This parameter is an integer representing x axis coordinates.
	 * @param j This parameter is an integer representing y axis coordinates.
	 * @return This returns a boolean representing escape. True, if escaped. False, if not.
	 */
	public static boolean escape(int i, int j){
		visited[i][j] = 's';
		boolean escaped = markSurrondings(i,j);
		if(escaped){
			e = 1;
			return true;
		}
		int[][] xy = move(i,j);
		for(int k = 0; k < xy.length; k++){
			escape(xy[k][0],xy[k][1]);
		}
		return false;
	}

	/**
	 * This function marks a piece's immediate surroundings. It is a helper method to checkEncircled.
	 * @param i This parameter is an integer representing x axis coordinates
	 * @param j This parameter is an integer representing y axis coordinates
	 * @return This returns a boolean representing escape(finding edge of board). True, if escaped. False, if not.
	 */
	public static boolean markSurrondings(int i, int j){
		if((i + 1 >= 0) && (i + 1 < GRID_SIZE)){
			mark(i+1, j);
		}
		if((i - 1 >= 0) && (i - 1 < GRID_SIZE)){
			mark(i-1, j);
		}
		if((j + 1 >= 0) && (j + 1 < GRID_SIZE)){
			mark(i, j+1);
		}
		if((j - 1 >= 0) && (j - 1 < GRID_SIZE)){
			mark(i, j-1);
		}
		if(i + 1 >= GRID_SIZE || i - 1 < 0 || j + 1 >= GRID_SIZE || j - 1 < 0){
			e = 1;
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * This function marks a spot on the visited grid based off of what piece is on the gameboard. It is a helper method
     * to checkEncircled.
	 * @param i This parameter is an integer representing x axis coordinates
	 * @param j This parameter is an integer representing y axis coordinates
	 */
	public static void mark(int i, int j){
		if(visited[i][j] == '0'){
			if(gameBoardArray[i][j] == attackers || gameBoardArray[i][j] == restricted){
				visited[i][j] = 'a';
			}
			else if (gameBoardArray[i][j] == defenders || gameBoardArray[i][j] == king){
				visited[i][j] = 't';
			}
			else if(gameBoardArray[i][j] == empty){
				visited[i][j] = 'u';
			}
		}
	}

    /**
     * This function simulates moving to a unseen part of the board.
     * @param i This parameter is an integer representing x axis coordinates.
     * @param j This parameter is an integer representing y axis coordinates.
     * @return This returns an int[][] representing the set of coordinates moved to.
     */
	public static int[][] move(int i, int j){
		int[][] xy = new int[4][2];
		int num = 0;
		if(visited[i+1][j] == 'u' || visited[i+1][j] == 't'){
			xy[num][0] = i + 1;
			xy[num][1] = j;
			num++;
		}
		if(visited[i-1][j] == 'u' || visited[i-1][j] == 't'){
			xy[num][0] = i - 1;
			xy[num][1] = j;
			num++;
		}
		if(visited[i][j+1] == 'u' || visited[i][j+1] == 't'){
			xy[num][0] = i;
			xy[num][1] = j+1;
			num++;
		}
		if(visited[i][j-1] == 'u' || visited[i][j-1] == 't'){
			xy[num][0] = i ;
			xy[num][1] = j-1;
			num++;
		}
		int[][] r = new int[num][2];
		for(int k = 0; k < num; k++){
			r[k][0] = xy[k][0];
			r[k][1] = xy[k][1];
		}
		return r;
	}

    /**
     *  This function sets the visited grid to all '0'.
     *  '0' represents unseen.
     */
	public static void setGrid(){
		for(int i = 0; i < GRID_SIZE; i++){
			for(int j = 0; j < GRID_SIZE; j++){
				visited[i][j] = '0';
			}
		}
	}
	
	//*END
	
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
			updateGameBoard(startCol, startRow, restricted);

		}else{
            updateGameBoard(startCol, startRow, empty);
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
     *                     i.e. another black piece if piece is black
     *                          the king piece if piece == defenders
     *                          another white piece if piece is white
     */
    private static void findShieldwall(char piece, int col, int row, char capturePiece, char helperPiece) {
        LinkedList<Integer> capturedPieceCol = new LinkedList<>();
        LinkedList<Integer> capturedPieceRow = new LinkedList<>();
        int counter = 0;

        if (col == 0) {
            if (row <= 7) {
                for (int i = row+1; i < 11; i++) {
                    if (gameBoardArray[col][i] == empty) {
                        break;
                    }
                    else if (gameBoardArray[col][i] == capturePiece && (gameBoardArray[col+1][i] == piece || gameBoardArray[col+1][i] == helperPiece)) {
                        capturedPieceCol.add(col);
                        capturedPieceRow.add(i);
                        counter++;
                    }
                    else if (gameBoardArray[col][i] == piece || gameBoardArray[col][i] == helperPiece || gameBoardArray[col][i] == restricted) {
                        if (counter >= 2) {
                            removeCapturedPieces(capturedPieceCol, capturedPieceRow);
                        }
                        break;
                    }
                }
            }
            if (row >= 3) {
                for (int i = row-1; i >= 0; i--) {
                    if (gameBoardArray[col][i] == empty) {
                        break;
                    }
                    else if (gameBoardArray[col][i] == capturePiece && (gameBoardArray[col+1][i] == piece || gameBoardArray[col+1][i] == helperPiece)) {
                        capturedPieceCol.add(col);
                        capturedPieceRow.add(i);
                        counter++;
                    }
                    else if (gameBoardArray[col][i] == piece || gameBoardArray[col][i] == helperPiece || gameBoardArray[col][i] == restricted) {
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
                    if (gameBoardArray[col][i] == empty) {
                        break;
                    }
                    else if (gameBoardArray[col][i] == capturePiece && (gameBoardArray[col-1][i] == piece || gameBoardArray[col-1][i] == helperPiece)) {
                        capturedPieceCol.add(col);
                        capturedPieceRow.add(i);
                        counter++;
                    }
                    else if (gameBoardArray[col][i] == piece || gameBoardArray[col][i] == helperPiece || gameBoardArray[col][i] == restricted) {
                        if (counter >= 2) {
                            removeCapturedPieces(capturedPieceCol, capturedPieceRow);
                        }
                        break;
                    }
                }
            }
            if (row >= 3) {
                for (int i = row-1; i >= 0; i--) {
                    if (gameBoardArray[col][i] == empty) {
                        break;
                    }
                    else if (gameBoardArray[col][i] == capturePiece && (gameBoardArray[col-1][i] == piece || gameBoardArray[col-1][i] == helperPiece)) {
                        capturedPieceCol.add(col);
                        capturedPieceRow.add(i);
                        counter++;
                    }
                    else if (gameBoardArray[col][i] == piece || gameBoardArray[col][i] == helperPiece || gameBoardArray[col][i] == restricted) {
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
                    if (gameBoardArray[i][row] == empty) {
                        break;
                    }
                    else if (gameBoardArray[i][row] == capturePiece && (gameBoardArray[i][row+1] == piece || gameBoardArray[i][row+1] == helperPiece)) {
                        capturedPieceCol.add(i);
                        capturedPieceRow.add(row);
                        counter++;
                    }
                    else if (gameBoardArray[i][row] == piece || gameBoardArray[i][row] == helperPiece || gameBoardArray[i][row] == restricted) {
                        if (counter >= 2) {
                            removeCapturedPieces(capturedPieceCol, capturedPieceRow);
                        }
                        break;
                    }
                }
            }
            if (col >= 3) {
                for (int i = col-1; i >= 0; i--) {
                    if (gameBoardArray[i][row] == empty) {
                        break;
                    }
                    else if (gameBoardArray[i][row] == capturePiece && (gameBoardArray[i][row+1] == piece || gameBoardArray[i][row+1] == helperPiece)) {
                        capturedPieceCol.add(i);
                        capturedPieceRow.add(row);
                        counter++;
                    }
                    else if (gameBoardArray[i][row] == piece || gameBoardArray[i][row] == helperPiece || gameBoardArray[i][row] == restricted) {
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
                    if (gameBoardArray[i][row] == empty) {
                        break;
                    }
                    else if (gameBoardArray[i][row] == capturePiece && (gameBoardArray[i][row-1] == piece || gameBoardArray[i][row-1] == helperPiece)) {
                        capturedPieceCol.add(i);
                        capturedPieceRow.add(row);
                        counter++;
                    }
                    else if (gameBoardArray[i][row] == piece || gameBoardArray[i][row] == helperPiece || gameBoardArray[i][row] == restricted) {
                        if (counter >= 2) {
                            removeCapturedPieces(capturedPieceCol, capturedPieceRow);
                        }
                        break;
                    }
                }
            }
            if (col >= 3) {
                for (int i = col-1; i >= 0; i--) {
                    if (gameBoardArray[i][row] == empty) {
                        break;
                    }
                    else if (gameBoardArray[i][row] == capturePiece && (gameBoardArray[i][row-1] == piece || gameBoardArray[i][row-1] == helperPiece)) {
                        capturedPieceCol.add(i);
                        capturedPieceRow.add(row);
                        counter++;
                    }
                    else if (gameBoardArray[i][row] == piece || gameBoardArray[i][row] == helperPiece || gameBoardArray[i][row] == restricted) {
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
	    char kingPiece = attackers;
	    char helperPiece;
	    LinkedList<Integer> capturedPieceCol = new LinkedList<>();
        LinkedList<Integer> capturedPieceRow = new LinkedList<>();
	    if (piece == attackers) {
	        capturablePiece = defenders;
            kingPiece = king;
            helperPiece = attackers;
        }
        else if (piece == defenders){
            capturablePiece = attackers;
            helperPiece = king;
        }
        else {
            capturablePiece = attackers;
            helperPiece = defenders;
        }
        if (col-2 >= 0) {
            if ((gameBoardArray[col - 1][row] == capturablePiece || gameBoardArray[col - 1][row] == kingPiece) && (gameBoardArray[col - 2][row] == piece || gameBoardArray[col - 2][row] == helperPiece || gameBoardArray[col - 2][row] == restricted)) {
                capturedPieceCol.add(col - 1);
                capturedPieceRow.add(row);
            }
        }
        if (col+2 <= GRID_SIZE-1) {
            if ((gameBoardArray[col + 1][row] == capturablePiece || gameBoardArray[col + 1][row] == kingPiece) && (gameBoardArray[col + 2][row] == piece || gameBoardArray[col + 2][row] == helperPiece || gameBoardArray[col + 2][row] == restricted)) {
                capturedPieceCol.add(col + 1);
                capturedPieceRow.add(row);
            }
        }
        if (row-2 >= 0) {
            if ((gameBoardArray[col][row - 1] == capturablePiece || gameBoardArray[col][row - 1] == kingPiece) && (gameBoardArray[col][row - 2] == piece || gameBoardArray[col][row - 2] == helperPiece || gameBoardArray[col][row - 2] == restricted)) {
                capturedPieceCol.add(col);
                capturedPieceRow.add(row - 1);
            }
        }
        if (row+2 <= GRID_SIZE-1) {
            if ((gameBoardArray[col][row + 1] == capturablePiece || gameBoardArray[col][row + 1] == kingPiece) && (gameBoardArray[col][row + 2] == piece || gameBoardArray[col][row + 2] == helperPiece || gameBoardArray[col][row + 2] == restricted)) {
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

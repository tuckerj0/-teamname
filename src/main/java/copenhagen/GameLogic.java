package copenhagen;

import java.util.LinkedList;

//Class does all of the logic involved in the Hnefatafl gameplay
public class GameLogic{

    //TODO Add test
    /**
     * This function checks whether a piece is allowed to be currently moved.
     * @param piece This is piece that is trying to be moved.
     * @return This function will return true if the piece can be moved, else false if it can not be moved.
     */
    public static boolean pieceCanMove(char piece, char turn) {
        if ((piece == 'w' && turn == 'w') || (piece == 'b' && turn == 'b') || (piece == 'k' && turn == 'w')) {
            return true;
        } else {
            return false;
        }
    }

    //TODO Add test
    /**
     * This function removes all the pieces that were captured on the board by the move just completed.
     * @param col This parameter is the column associated with a piece that is about to be removed.
     * @param row This parameter is the row associated with a piece that is about to be removed.
     */
	public static char[][] removeCapturedPieces(LinkedList<Integer> col, LinkedList<Integer> row, char[][] pieceLayout) {
        GameBoard hBoard = Hnefatafl.getHBoard();
	    for (int i = 0; i < col.size(); i++) {
	        int c = col.get(i);
	        int r = row.get(i);
            pieceLayout[c][r] = '0';
			GameBoard.removeCapturedPiecesUI(c,r);
		}
        return pieceLayout;
    }

}

package copenhagen;

//Class does all of the logic involved in the Hnefatafl gameplay
public class GameLogic{
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
}

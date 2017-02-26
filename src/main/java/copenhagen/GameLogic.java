package copenhagen;

//Class does all of the logic involved in the Hnefatafl gameplay
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
}

/**
 * This program is a Java version of the ancient Norse game hnefatafl ("table of the fist", in Old Norse). The game is
 * played by Copenhagen rules (11 x 11 square board).
 *
 * @author $team
 * Jennifer Fang, JFang1
 * Peter McCloskey, pcm17
 * Jason Tucker, tuckerj0
 * Guiseppe Zielinski, gcz3
 *
 * CS 1530 - Software Engineering
 * Spring Semester 2017
 */

package copenhagen;

import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JButton;

/**
 * This is the main file that runs the program.
 */
public class Hnefatafl {
	private static JFrame frame;
	private static JPanel board;
	private static JPanel side;
	private static JPanel bottom;
	private static JMenuBar menuBar;
	private static boolean saved = true;
	private static int boardSize = 11;
	private static int turnCount = 1;
	private static char turn = 'b';
	private static GameBoard hBoard;
  	private static SideBar sBar;
	private static int[] primaryColor = {244,164,96};
	private static int[] secondaryColor = {139,69,19};
	private static int[] letteringColor = {0,0,0};
	private static int[] specialColor = {0,0,88};
	private static BoardLocation selectedLoc;
	private static JButton selected;
	private static boolean pieceIsSelected = false;
	private static char winner;
	private static FinalMenu finalMenu;

    /**
     * This is the main method which starts the program.
     * @param args Unused.
     */
	public static void main(String[] args) {
		new MainMenu();
	}

    /**
     * This function disposes of the old UI display of the game when a new game is loaded if a board is already present
     * on the screen.
     */
	public static void removeOldGameBoard() {
	    frame.dispose();
    }

    /**
     * This function sets up and retrieves the different pieces (3 JPanels) that comprise the game board.
     */
	public static void setUpGameBoard() {
        hBoard = new GameBoard(boardSize, primaryColor, secondaryColor, specialColor);
        board = hBoard.getBoard();
        sBar = new SideBar(primaryColor, secondaryColor, letteringColor);
		side = sBar.getSideBar();
		BottomBar bBar = new BottomBar(primaryColor, letteringColor, turn, turnCount);
		bottom = bBar.getBottomBar();
		MenuBar menu = new MenuBar();
		menuBar = menu.getMenuBar();
		selectedLoc = new BoardLocation();
	}

    /**
     * This function adds the different pieces (3 JPanels) to the main JFrame of the program and displays the game to
     * the user.
     */
	public static void displayGameBoard() {
		/*Initialize JFrame. This will hold 3 JPanels*/
		frame = new JFrame("Hnefatafl");
		/*Add Board to lefthand side of JFrame*/
		frame.add(board, BorderLayout.LINE_START);
		frame.add(side, BorderLayout.EAST);
		frame.add(bottom, BorderLayout.SOUTH);
		/* Add Menu bar at top of JFrame*/
		frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
		frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}

    /**
     * This function changes whose turn it is at the end of each move and updates turn info appropriately.
     * b = black = attackers
     * w = white = king and his defenders
     */
	public static int endTurn() {
	    if (turn == 'b') {
	        turn = 'w';
        }
        else if (turn == 'w') {
	        turn = 'b';
        }
		turnCount++;
		BottomBar.updateTurnInfo(turn, turnCount);
		winner = GameLogic.checkWinner();
		if (winner != '0') {
			finalMenu = new FinalMenu(winner);
		}
		saved = false;
		return turnCount;
    }

    /**
     * This is a getter that gets whose turn it currently is.
     * @return This function will return 'b' if it is the attackers turn or 'w' if it is the defenders turn.
     */
    public static char getTurn() {
	    return turn;
    }

    /**
     * This is a setter that sets whose turn it currently is when loading a game save.
     * @param c This parameter is whose turn it left off that when the game was saved.
     */
    public static void setTurn(char c) {
	    turn = c;
    }

    /**
     * This is a setter that sets the current turn number when loading a game save.
     * @param i This parameter is the turn count for the game.
     */
    public static void setTurnCount(int i) {
	    turnCount = i;
    }

    /**
     * This is a getter that gets whether the game was saved already before exiting.
     * @return This will return true it the game was already saved. Otherwise, false.
     */
    public static boolean getSaved() {
        return saved;
    }

    /**
     * This function is called whenever a square is clicked on the game board.
     * @param c This is the column of the square clicked.
     * @param r This is the row of the square clicked.
     * @param clickedOn This is the specific piece (JButton) that was clicked on.
     */
	public static void squareClicked(int c, int r, JButton clickedOn){
		GameBoard.unselectLast(pieceIsSelected, selectedLoc, selected); // unselects last piece
		// highlighting valid moves for the newly chosen piece
		char chosenSquaresPiece = GameLogic.getPiece(c, r);
		if (GameLogic.pieceCanMove(chosenSquaresPiece,turn)) {
			boolean[][] highlight = GameLogic.getValidMoves(chosenSquaresPiece, c, r);
			for(int i = 0; i < highlight.length; i++){
				for(int j = 0; j < highlight[0].length; j++){
					if(highlight[i][j] == true){
						hBoard.highlightButton(i,j);
					}
				}
			}
		}
		// moves piece if appropriate
		if((chosenSquaresPiece == '0' || chosenSquaresPiece == 'c') && pieceIsSelected){
			movePiece(c,r);
		}
		selectNew(clickedOn,c,r);
	}

    /**
     * This function is called to try and move the selected piece (selectedLoc) if it is a valid move.
     * @param c This parameter is the column they are trying to move to.
     * @param r This parameter is the row they are trying to move to.
     */
	public static void movePiece(int c, int r){
        char pieceType = GameLogic.getGameBoardArray()[selectedLoc.getColumn()][selectedLoc.getRow()];
        boolean[][] validMoves = GameLogic.getValidMoves(pieceType, selectedLoc.getColumn(),selectedLoc.getRow());
		if(validMoves[c][r] == true){
			GameLogic.movePieceOnBoard(selectedLoc.getColumn(),selectedLoc.getRow(),c,r);
			GameBoard.movePieceOnBoard(selectedLoc.getColumn(),selectedLoc.getRow(),c,r,pieceType);
            endTurn();
		}else{
			JOptionPane.showMessageDialog(null, "Invalid Move");
		}
	}

    

	

    /**
     * This function sets a new game piece to the selected game piece.
     * @param clickedOn This parameter is the game piece (JButton) that is clicked on.
     * @param c This parameter is the column of the game piece that is clicked on.
     * @param r This parameter is the row of the game piece that is clicked on.
     */
	public static void selectNew(JButton clickedOn,int c, int r){
		char piece = GameLogic.getPiece(c, r);

        try {
			selectedLoc.setColumn(c);
			selectedLoc.setRow(r);
			selected = clickedOn;
			Image img;
			ImageIcon icon;
			pieceIsSelected = true;
			if(piece == 'b' && turn == 'b'){
				img = ImageIO.read(Hnefatafl.class.getResource("images/blackpieceSelected.png"));
				icon = new ImageIcon(img);
				clickedOn.setIcon(icon);
			}else if(piece == 'w' && turn == 'w'){
				img = ImageIO.read(Hnefatafl.class.getResource("images/whitepieceSelected.png"));
				icon = new ImageIcon(img);
				clickedOn.setIcon(icon);
			}else if(piece == 'k' && turn == 'w'){
				img = ImageIO.read(Hnefatafl.class.getResource("images/kingSelected.png"));
				icon = new ImageIcon(img);
				clickedOn.setIcon(icon);
			}else{
				pieceIsSelected = false;
			}
		} catch (IOException e) {
			System.out.println("Image Didn't Load");
			System.exit(1);
		}
	}

    /**
     * This function saves the present game state to a save file.
     * @return This function will return true if successful or false in the case of an IOException.
     */
	public static boolean saveGame(SaveAndLoad sl) {
	    saved = sl.save(boardSize, turn, turnCount);
		return saved;
	}

    /**
     * This function loads a game state from a file and validates it.
     * It must have a .hnef extension to be accepted.
     * @return The return value is a boolean representing success or failure
     */
	public static boolean loadGame(SaveAndLoad sl) {
		File loadFile = sl.load();
		if(loadFile == null){
			return false;
		}
		return true;
	}

    /**
     * This function begins a new game.
     */
	public static void newGame(){
		newGameResetTurns();
		newGameGUI();
	}

	/**
	 * This function sets up the logic for a new game.
	 * @return The return value is the reset turn count (which should always be 1)
	 */
	public static int newGameResetTurns(){
		turnCount = 1;
		turn = 'b';
		saved = true;
		return turnCount;
	}

	/**
	 * This function sets up the GUI for a new game.
	 */
	public static void newGameGUI(){
		frame.remove(bottom);
		BottomBar bBar = new BottomBar(primaryColor, letteringColor, turn, turnCount);
		bottom = bBar.getBottomBar();
		frame.remove(board);
        GameLogic.setStartingPieces(boardSize);
        hBoard = new GameBoard(boardSize, primaryColor, secondaryColor, specialColor);
		board = hBoard.getBoard();
		frame.add(board, BorderLayout.LINE_START);
		frame.add(bottom, BorderLayout.SOUTH);
		frame.pack();
	}

    /**
     * This functions gets the HBoard.
     * @return This function will return the jPanel that is displayed to the user of the current gameboard.
     */
	public static GameBoard getHBoard(){
		return hBoard;
	}
}

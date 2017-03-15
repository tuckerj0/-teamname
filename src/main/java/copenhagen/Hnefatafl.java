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
	private static char attackers = 'b';
	private static char defenders = 'w';
	private static char king = 'k';
	private static char empty = '0';
	private static char restricted = 'c';
	private static char turn = attackers;
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
	private static String attackPieceAddr = "images/blackpiece.png";
	private static String defendPieceAddr = "images/whitepiece.png";
	private static String kingPieceAddr = "images/king.png";
	private static String attackSelAddr = "images/blackpieceSelected.png";
	private static String defendSelAddr = "images/whitepieceSelected.png";
	private static String kingSelAddr = "images/kingSelected.png";

    /**
     * This is the main method which starts the program.
     * @param args Unused.
     */
	public static void main(String[] args) {
		new MainMenu();
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
	    if (turn == attackers) {
	        turn = defenders;
        }
        else if (turn == defenders) {
	        turn = attackers;
        }
		turnCount++;
		BottomBar.updateTurnInfo(turn, turnCount);
		winner = GameLogic.checkWinner();
		if (winner != empty) {
			finalMenu = new FinalMenu(winner);
		}
		saved = false;
		return turnCount;
    }

    /**
     * This is a getter that gets whose turn it currently is.
     * @return This function will return 'attackers' if it is the attackers turn or 'defenders' if it is the defenders' turn.
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
		if((chosenSquaresPiece == empty || chosenSquaresPiece == restricted) && pieceIsSelected){
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
     * This function sets the icon of a particular button.
     * @param pieceType The value of the piece from the characters specified in GameBoard.java.
     * @param button The button to add the image to.
     */
	public static void setButtonImage(char pieceType, JButton button){
		try {
			Image img;
			ImageIcon icon;
			if(pieceType == attackers){
				img = ImageIO.read(Hnefatafl.class.getResource(attackPieceAddr));
				icon = new ImageIcon(img);
				button.setIcon(icon);
			}else if(pieceType == defenders){
				img = ImageIO.read(Hnefatafl.class.getResource(defendPieceAddr));
				icon = new ImageIcon(img);
				button.setIcon(icon);
			}else if(pieceType == king){
				img = ImageIO.read(Hnefatafl.class.getResource(kingPieceAddr));
				icon = new ImageIcon(img);
				button.setIcon(icon);
			}
		} catch (IOException e) {
			System.out.println("Image Didn't Load");
			System.exit(1);
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
			if(piece == attackers && turn == attackers){
				img = ImageIO.read(Hnefatafl.class.getResource(attackSelAddr));
				icon = new ImageIcon(img);
				clickedOn.setIcon(icon);
			}else if(piece == defenders && turn == defenders){
				img = ImageIO.read(Hnefatafl.class.getResource(defendSelAddr));
				icon = new ImageIcon(img);
				clickedOn.setIcon(icon);
			}else if(piece == king && turn == defenders){
				img = ImageIO.read(Hnefatafl.class.getResource(kingSelAddr));
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
		turn = attackers;
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
     * This functionss gets the HBoard.
     * @return This function will return the jPanel that is displayed to the user of the current gameboard.
     */
	public static GameBoard getHBoard(){
		return hBoard;
	}

	/**
     * This functionss gets the attack piece image address.
     * @return This function will return the String for the attack piece image address.
     */
	public static String getAttackPieceAddr(){
		return attackPieceAddr;
	}

	/**
	 * This functionss gets the defend piece image address.
	 * @return This function will return the String for the defend piece image address.
	 */
	public static String getDefendPieceAddr(){
		return defendPieceAddr;
	}

	/**
     * This functionss gets the king piece image address.
     * @return This function will return the String for the king piece image address.
     */
	public static String getKingPieceAddr(){
		return kingPieceAddr;
	}

	/**
     * This functionss gets the selected attack piece image address.
     * @return This function will return the String for the selected attack piece image address.
     */
	public static String getAttackSelAddr(){
		return attackSelAddr;
	}

	/**
     * This functionss gets the selected defend piece image address.
     * @return This function will return the String for the selected defend piece image address.
     */
	public static String getDefendSelAddr(){
		return defendSelAddr;
	}

	/**
     * This functionss gets the selected king piece image address.
     * @return This function will return the String for the selected king piece image address.
     */
	public static String getKingSelAddr(){
		return kingSelAddr;
	}
}

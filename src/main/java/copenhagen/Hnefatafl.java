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
	private static boolean saved = true;
	private static int boardSize = 11;
	private static int turnCount = 2;
	private static char attackers = 'b';
	private static char defenders = 'w';
	private static char king = 'k';
	private static char empty = '0';
    private static char draw = 'd';
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
	private static BottomBar bBar = new BottomBar(primaryColor, letteringColor, turn, turnCount);
    private static int numTimers = 0;
    
	//Piece colors set to black and white by default, can be changed in the settings menu
	private static String attackColor = "black";
	private static String defenseColor = "white";
	private static ImageIcon attackerImage;
	private static ImageIcon defenderImage;
	private static ImageIcon kingImage;
	private static ImageIcon attackerSelectedImage;
	private static ImageIcon defenderSelectedImage;
	private static ImageIcon kingSelectedImage;

    /**
     * This is the main method which starts the program.
     * @param args Unused.
     */
	public static void main(String[] args) {
		new MainMenu();
        if(!attackColor.equals("Custom")){
            setAttackColor(attackColor);
        }
        if(!defenseColor.equals("Custom")){
            setDefenseColor(defenseColor);
        }
        setKingImage();
	}

    /**
     * This function disposes of the old UI display of the game when a new game is loaded if a board is already present
     * on the screen.
     */
	public static void removeOldGameBoard() {
	    frame.dispose();
	    pieceIsSelected = false;
    }

    /**
     * This function sets up the primary and secondary game board colors according to the settings.
     * @param primaryColorR This parameter is the red of the primary color.
     * @param primaryColorG This parameter is the green of the primary color.
     * @param primaryColorB This parameter is the blue of the primary color.
     * @param secondaryColorR This parameter is the red of the secondary color.
     * @param secondaryColorG This parameter is the green of the secondary color.
     * @param secondaryColorB This parameter is the blue of the secondary color.
     */
	public static void setBoardColors(int primaryColorR, int primaryColorG, int primaryColorB, int secondaryColorR, int secondaryColorG, int secondaryColorB) {
		primaryColor[0] = primaryColorR;
		primaryColor[1] = primaryColorG;
		primaryColor[2] = primaryColorB;
		secondaryColor[0] = secondaryColorR;
		secondaryColor[1] = secondaryColorG;
		secondaryColor[2] = secondaryColorB;
	}

    /**
     * This method sets the text/lettering colors.
     * @param letteringColorR This parameter is the red of color.
     * @param letteringColorG This parameter is the green of color.
     * @param letteringColorB This parameter is the blue of color.
     */
	 public static void setLetteringColor(int letteringColorR, int letteringColorG, int letteringColorB) {
		letteringColor[0] = letteringColorR;
		letteringColor[1] = letteringColorG;
		letteringColor[2] = letteringColorB;
	 }

    /**
     * This function sets up and retrieves the different pieces (3 JPanels) that comprise the game board.
     */
	public static void setUpGameBoard() {
        hBoard = new GameBoard(boardSize, primaryColor, secondaryColor, specialColor);
        board = hBoard.getBoard();
        sBar = new SideBar(primaryColor, secondaryColor, letteringColor);
		side = sBar.getSideBar();
		bBar = new BottomBar(primaryColor, letteringColor, turn, turnCount);
		bottom = bBar.getBottomBar();
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
		winner = GameLogic.checkWinner(turn, turnCount);
	    if (turn == attackers) {
	        turn = defenders;
        }
        else if (turn == defenders) {
	        turn = attackers;
        }
		turnCount++;
		BottomBar.updateTurnInfo(turn, turnCount);
		if (winner != empty) {
			finalMenu = new FinalMenu(winner);
		}
		saved = false;
		return turnCount;
    }

    /**
     * This is a getter that gets whose turn it currently is.
     * @return This function will return 'attackers' if it is the attackers turn or 'defenders' if it is the defenders'
     * turn.
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
	 * This is a setter that resets the current turn number to 0 when one or more pieces are captured.
	 */
	public static int captureResetTurnCount() {
		turnCount = 0;
        return turnCount;
	}

    /**
     * This is a getter that gets whether the game was saved already before exiting.
     * @return This will return true it the game was already saved. Otherwise, false.
     */
    public static boolean getSaved() {
        return saved;
    }

	/**
     * This is a getter that gets the size of the gameboard.
     * @return This will return an integer representing the length/width of the gameboard.
     */
    public static int getBoardSize() {
        return boardSize;
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
     * This function sets a new game piece to the selected game piece.
     * @param clickedOn This parameter is the game piece (JButton) that is clicked on.
     * @param c This parameter is the column of the game piece that is clicked on.
     * @param r This parameter is the row of the game piece that is clicked on.
     */
	public static void selectNew(JButton clickedOn,int c, int r){
		char piece = GameLogic.getPiece(c, r);
        selectedLoc.setColumn(c);
        selectedLoc.setRow(r);
        selected = clickedOn;
        pieceIsSelected = true;
        if (piece == attackers && turn == attackers) {
            clickedOn.setIcon(attackerSelectedImage);
        } else if(piece == defenders && turn == defenders) {
            clickedOn.setIcon(defenderSelectedImage);
        } else if(piece == king && turn == defenders) {
            clickedOn.setIcon(kingSelectedImage);
        } else {
            pieceIsSelected = false;
        }
	}

    /**
     * This function saves the present game state to a save file.
     * @param sl This parameter is a SaveAndLoad object which will be used to actually save the file.
     * @return This function will return true if successful or false in the case of an IOException.
     */
	public static boolean saveGame(SaveAndLoad sl) {
		String aClock = bBar.getAttackersClock().getTime();
		String dClock = bBar.getDefendersClock().getTime();
	    saved = sl.save(boardSize, turn, turnCount, aClock, dClock);
		return saved;
	}

    /**
     * This function loads a game state from a file and validates it. It must have a .hnef extension to be accepted.
     * @param sl This parameter is a SaveAndLoad object which will be used to actually load the file.
     * @return The return value is a boolean representing success or failure.
     */
	public static boolean loadGame(SaveAndLoad sl) {
		File loadFile = sl.load();
		if(loadFile == null){
			return false;
		}
		return true;
	}

    /**
     * The function sets attack and defense clocks to given times.
     * @param aTime Formatted string representing time on attacker's clock.
     * @param dTime Formatted string representing time on defender's clock.
     */
	public static void changeTimes(String aTime, String dTime){
		bBar.setClocks(aTime, dTime);
	}

    /**
     * The function restarts attack and defense clocks beginning at given times.
     * @param aTime Formatted string representing time on attacker's clock.
     * @param dTime Formatted string representing time on defender's clock.
     */
    public static void restartTimers(String aTime, String dTime) {
        bBar.restartClocks(aTime, dTime);
    }
    
    /**
     * The function increments the number of timers by one.
     * @return This function will return the current number of timers
     */
    public static int incrementTimers() {
        return numTimers++;
    }
    
    /**
     * The function decrements the number of timers by one.
     * @return This function will return the current number of timers
     */
    public static int decrementTimers() {
        return numTimers--;
    }
    
    /**
     * The function decrements the number of timers by one.
     * @return This function will return the current number of timers
     */
    public static int getNumTimers() {
        return numTimers;
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
     * This function gets the HBoard.
     * @return This function will return the jPanel that is displayed to the user of the current gameboard.
     */
	public static GameBoard getHBoard(){
		return hBoard;
	}

	/**
	 * This function set the color of the attacking pieces on the board.
	 * @param color This parameter is a String representing the color of the piece.
	 */
	public static void setAttackColor(String color){
		color = color.toLowerCase();
		attackColor = color;
		String address = "images/";
		address = address + color;
		String selectedAddress = address + "pieceSelected.png";
		address = address + "piece.png";
		try{
			attackerImage = new ImageIcon(ImageIO.read(Hnefatafl.class.getResource(address)));
			attackerSelectedImage = new ImageIcon(ImageIO.read(Hnefatafl.class.getResource(selectedAddress)));
		}
		catch(IOException e) {
			JOptionPane.showMessageDialog(null,"Can't Find Attacker Image.");
		}
	}

    /**
     * This method sets the images that will be used for the king piece.
     */
	public static void setKingImage() {
	    try {
            kingImage = new ImageIcon(ImageIO.read(Hnefatafl.class.getResource("images/king.png")));
            kingSelectedImage = new ImageIcon(ImageIO.read(Hnefatafl.class.getResource("images/kingSelected.png")));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Can't Find King Image.");
        }
    }

	/**
	 * This function set the color of the defending pieces on the board.
     * @param color This parameter is a String representing the color of the piece.
	 */
	public static void setDefenseColor(String color) {
		color = color.toLowerCase();
		defenseColor = color;
		String address = "images/";
		address = address + color;
		String selectedAddress = address + "pieceSelected.png";
		address = address + "piece.png";
		try{
			defenderImage = new ImageIcon(ImageIO.read(Hnefatafl.class.getResource(address)));
			defenderSelectedImage = new ImageIcon(ImageIO.read(Hnefatafl.class.getResource(selectedAddress)));
		}
		catch(IOException e) {
			JOptionPane.showMessageDialog(null,"Can't Find Defender Image.");
		}
	}

	/**
     * This is a getter that gets the attacker piece color.
     * @return This function will return the String for the attack piece color.
     */
	public static String getAttackColor(){
		return attackColor;
	}

	/**
     * This is a getter that gets the defender piece color.
     * @return This function will return the String for the attack piece color.
     */
	public static String getDefenseColor(){
		return defenseColor;
	}

    /**
     * This is a getter that gets the attacker piece image.
     * @return This method will return the ImageIcon that represents a attack piece.
     */
	public static ImageIcon getAttackerImage() {
		return attackerImage;
	}

    /**
     * This is a getter that gets the defender piece image.
     * @return This method will return the ImageIcon that represents a defender piece.
     */
	public static ImageIcon getDefenderImage() {
		return defenderImage;
	}

    /**
     * This is a getter that gets the king piece image.
     * @return This method will return the ImageIcon that represents the king piece.
     */
	public static ImageIcon getKingImage() {
	    return kingImage;
    }

    /**
     * This is a setter that sets the image used for the attacking pieces.
     * @param img This parameter is the ImageIcon that will represent the attacking pieces.
     */
	public static void setAttackerImage(ImageIcon img) {
        attackerImage = img;
        attackerSelectedImage = img;
        attackColor = "Custom";
	}

    /**
     * This is a setter that sets the image used for the defending pieces.
     * @param img This parameter is the ImageIcon that will represent the defending pieces.
     */
	public static void setDefenderImage(ImageIcon img) {
        defenderImage = img;
        defenderSelectedImage = img;
        defenseColor = "Custom";
	}
}

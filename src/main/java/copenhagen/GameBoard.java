package copenhagen;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.event.*;

/**
 * This class contains all UI for the game board. The game board is represented by a two dimensional
 * character array. The location in the array is the location on the board. The first dimension represents the row and
 * the second dimension represents the column.
 * The char value represents the piece type:
 * '0' for empty
 * 'c' for the throne and the corner squares (the five restricted squares)
 * 'w' for a white piece
 * 'b' for a black piece
 * 'k' for the king piece
 */
public class GameBoard {

	int gridSize;
	Color highlight = new Color(255,0,0);
	Color primaryColor;
	Color secondaryColor;
	Color specialColor; //color of the center and corners
	private JButton[][] boardSquares;
	private JPanel board;
	private char[][] pieceLocations;

    /**
     * This is called when creating the game board JPanel.
     * @param size This parameter represents the size of the game board.
     * @param pc This parameter represents the primary color of the game board.
     * @param sc This parameter represents the secondary color of the game board.
     * @param spc This parameter represents the color of the special center and corner squares on the game board.
     */
    public GameBoard(int size, int[] pc, int[] sc, int[] spc) {
		gridSize = size;
		primaryColor = new Color(pc[0], pc[1], pc[2]);
		secondaryColor = new Color(sc[0], sc[1], sc[2]);
		specialColor = new Color(spc[0], spc[1], spc[2]);
		pieceLocations = GameLogic.setStartingPieces(gridSize);
		initializeGUI(pieceLocations);
	}

    /**
     * This is a button listener for each square (JButton) on the game board that when clicked will then send the row
     * and column of that square to the Hnefatafl.squareClicked() function.
     */
	class squareClickedListener implements  ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			for (int i = 0; i < boardSquares.length; i++) {
			 	for (int j = 0; j < boardSquares.length; j++) {
			    	if (source == boardSquares[i][j]) {
						Hnefatafl.squareClicked(i,j,boardSquares[i][j]);
			     	}
			   }
			}
		}
	}

    /**
     * This function gets the JButton at a given location in the two dimensional array.
     * @param col This parameter represents the column of the JButton.
     * @param row This parameter represents the row of the JButton.
     * @return This function will return the JButton found at the given location.
     */
	public JButton getButtonByLocation(int col, int row){
		return boardSquares[col][row];
	}

    /**
     * This function initializes the graphical user interface representation of the game board when a game is started.
     * It calls the addPiece and setStartingPieces methods.
     * @return This function will return true if it is successful
     */
	public boolean initializeGUI(char[][] gameBoardArray) {
		board = new JPanel(new GridLayout(gridSize, gridSize));
		board.setBorder(new LineBorder(Color.BLACK));
		boardSquares = new JButton[gridSize][gridSize];

		Insets buttonMargin = new Insets(0,0,0,0);
        for (int i = 0; i < boardSquares.length; i++) {
            for (int j = 0; j < boardSquares[i].length; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                b.setPreferredSize(new Dimension(64, 64));
				b.setOpaque(true);
				b.setBorder(BorderFactory.createLineBorder(Color.gray));
				b.setBorderPainted(true);
				b.setMaximumSize(new Dimension(64, 64));
				b.setMinimumSize(new Dimension(64, 64));
				b.addActionListener(new squareClickedListener());

				addPiece(gameBoardArray[i][j],b);

				if ((i==0 && j==0)|| (i==gridSize-1 && j==gridSize-1)|| (i==0 && j==gridSize-1)
					|| (i==gridSize-1 && j==0) || (i==(gridSize/2) && j==(gridSize/2))){
					b.setBackground(specialColor);
				} else if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                    b.setBackground(primaryColor);
                } else {
                    b.setBackground(secondaryColor);
                }
                boardSquares[j][i] = b;
            }
        }
		for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                board.add(boardSquares[j][i]);
            }
        }
		return true;
	}

    /**
     * This function will "highlight" the square where a game piece is located at by changing the color at the game
     * piece's location. This effectively shows the valid moves a piece can make.
     * @param col This parameter represents the column of the square.
     * @param row This parameter represents the row of the square.
     */
	public void highlightButton(int col, int row){
		boardSquares[col][row].setBackground(highlight);
		boardSquares[col][row].setBorder(BorderFactory.createLineBorder(Color.gray));
		boardSquares[col][row].setBorderPainted(true);
	}

    /**
     * This function will "unhighlight" the square where a game piece is located at by changing the color at the game
     * piece's location.
     * @param col This parameter represents the column of the square.
     * @param row This parameter represents the row of the square.
     */
	public void unhighlightButton(int col, int row){
		if (pieceLocations[col][row] == 'c') {
		    boardSquares[col][row].setBackground(specialColor);

        }
	    else if((col % 2 == 1 && row % 2 == 1) || (col % 2 == 0 && row % 2 == 0)){
			boardSquares[col][row].setBackground(primaryColor);

		}
		else {
			boardSquares[col][row].setBackground(secondaryColor);

		}
	}

    /**
     * This function is called to return the JPanel representing the game board.
     * @return This returns the created JPanel representing the game board when the program is ran.
     */
	public JPanel getBoard(){
		return board;
	}

    /**
     * This function will give a game piece (the JButton) the appropriate image.
     * @param pieceName This parameter represents what type of game piece it is.
     * @param button This parameter represents the JButton that will represent the game piece.
     */
	private void addPiece(char pieceName, JButton button) {
	    try {
	        Image img;
	        ImageIcon icon;
            if (pieceName == 'b') {
                img = ImageIO.read(getClass().getResource("images/blackpiece.png"));
                icon = new ImageIcon(img);
                button.setIcon(icon);
            } else if (pieceName == 'w') {
                img = ImageIO.read(getClass().getResource("images/whitepiece.png"));
                icon = new ImageIcon(img);
                button.setIcon(icon);
            } else if (pieceName == 'k') {
                img = ImageIO.read(getClass().getResource("images/king.png"));
                icon = new ImageIcon(img);
                button.setIcon(icon);
            }
        }
        catch (IOException e) {
	        System.out.println("Unable to load image of piece. Please try running the program again");
	        System.exit(1);
        }
	}

    /**
     * This function gets the game board containing the location of every game piece.
     * @return This returns a two dimensional character array representing the game pieces and their location on the
     * game board.
     */
	public char[][] getPieceLocations(){
		return pieceLocations;
	}

    /**
     * This function gets the grid size of the game board.
     * @return This will return an integer representing the grid size.
     */
	public int getGridSize(){
		return gridSize;
	}
<<<<<<< HEAD
=======

    /**
     * This function sets the starting location of each game piece on the board.
     * @return This returns a two dimensional character array that represents the game board with game pieces at their
     * starting positions.
     */
	private char[][] setStartingPieces() {
		char[][] s = new char [gridSize][gridSize];
		//Initialize to null char
		for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
				s[i][j] = '0';
			}
		}

		if (gridSize==11) {
			s[0][3] = s[0][4] = s[0][5] = s[0][6] = s[0][7] = s[1][5] = 'b';
			s[3][0] = s[4][0] = s[5][0] = s[6][0] = s[7][0] = s[5][1] = 'b';
			s[10][3] = s[10][4] = s[10][5] = s[10][6] = s[10][7] = s[9][5] = 'b';
			s[3][10] = s[4][10] = s[5][10] = s[6][10] = s[7][10] = s[5][9] = 'b';

			s[3][5] = s[4][4] = s[4][5] = s[4][6] = s[5][3] = s[5][4] = 'w';
			s[5][6] = s[5][7] = s[6][4] = s[6][5] = s[6][6] = s[7][5] = 'w';
			s[0][0] = s[0][10] = s[10][0] = s[10][10] = s[5][5] = 'c';
			s[5][5] = 'k';
		}
		return s;
	}

	/**
     * This function unselects the previous piece when a new piece is selected.
	 * @param pieceIsSelected is true if the user has currently selected a gamePiece
	 * @param selectedLoc this holds the row and column location of the selected piece
	 * @param selected is the JButton of the currently selected piece
     */
	public static void unselectLast(boolean pieceIsSelected, int[] selectedLoc, JButton selected){
		GameBoard hBoard = Hnefatafl.getHBoard();
        char[][] pieceLayout = Hnefatafl.getPieceLayout();
		if(!pieceIsSelected){
			return;
		}
        char pieceType = pieceLayout[selectedLoc[0]][selectedLoc[1]];
        boolean[][] unhighlight = Hnefatafl.getValidMoves(pieceType, selectedLoc[0],selectedLoc[1]);
		for(int i = 0; i < unhighlight.length; i++){
			for(int j = 0; j < unhighlight[0].length; j++){
				if(unhighlight[i][j] == true){
					hBoard.unhighlightButton(i,j);
				}
			}
		}
		Hnefatafl.setButtonImage(pieceType,selected);
	}

	/**
     * This function will remove the gamePiece from a given square
     * @param c This parameter represents the column of the square.
     * @param r This parameter represents the row of the square.
     */
	public static void removeCapturedPiecesUI(int c, int r){
		GameBoard hBoard = Hnefatafl.getHBoard();
		JButton gamePiece = hBoard.getButtonByLocation(c,r);
		gamePiece.setIcon(null);
	}

>>>>>>> refs/remotes/origin/master
}

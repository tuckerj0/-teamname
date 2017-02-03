package copenhagen;

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.swing.border.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.event.*;

public class GameBoard {

	int gridSize;
	Color primaryColor;
	Color secondaryColor;
	Color letteringColor;
	Color specialColor; //color of the center and corners
	private JButton[][] boardSquares;
	private JPanel board;
	private char[][] pieceLocations;

    public GameBoard(int size, int[] pc, int[] sc, int[] lc, int[] spc) {
		gridSize = size;
		primaryColor = new Color(pc[0], pc[1], pc[2]);
		secondaryColor = new Color(sc[0], sc[1], sc[2]);
		letteringColor = new Color(lc[0], lc[1], lc[2]);
		specialColor = new Color(spc[0], spc[1], spc[2]);
		initializeGUI();
	}

	//Listens for square on gameboard to be clicked then sends row and colum to squareClicked()
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
	*Initializes graphical user interface representation of board.
	*Calls addpiece and setStartingPieces methods.
	*
	*@param void
	*@return true if successful
	*/
	public boolean initializeGUI() {
		board = new JPanel(new GridLayout(gridSize, gridSize));
		board.setBorder(new LineBorder(Color.BLACK));
		boardSquares = new JButton[gridSize][gridSize];

		pieceLocations = setStartingPieces();

		Insets buttonMargin = new Insets(0,0,0,0);
        for (int i = 0; i < boardSquares.length; i++) {
            for (int j = 0; j < boardSquares[i].length; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                b.setPreferredSize(new Dimension(64, 64));
				b.addActionListener(new squareClickedListener());

				addPiece(pieceLocations[i][j],b);

				if ((i==0 && j==0)|| (i==gridSize-1 && j==gridSize-1)|| (i==0 && j==gridSize-1)
					|| (i==gridSize-1 && j==0) || (i==(gridSize/2) && j==(gridSize/2))){
					b.setBackground(specialColor);
				} else if ((j % 2 == 1 && i % 2 == 1)|| (j % 2 == 0 && i % 2 == 0)) {
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


	//returns the gameboard
	public JPanel getBoard(){
		return board;
	}


	//If there is a piece in this location it adds it to the button
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
	*Returns the board location of every piece using a 2D array.
	*Char value repesents piece type. '0' for no piece, 'w' for white piece,
	*'b' for black piece, and 'k' for king piece. Location in the array is the location
	*on the board. First dimension represents row, second dimension represents column.
	*
	*@param void
	*@return 2D array repesenting board pieces and their location
	*/
	public char[][] getPieceLocations(){
		return pieceLocations;
	}
	/**
	*Returns the grid size.
	*
	*@param void
	*@return int representing grid size
	*/
	public int getGridSize(){
		return gridSize;
	}
	//Sets the location of each starting piece
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

			s[5][5] = 'k';
		}
		return s;
	}
}

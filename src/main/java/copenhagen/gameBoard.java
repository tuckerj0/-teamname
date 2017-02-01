package copenhagen;

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.swing.border.*;

public class GameBoard {

	int gridSize;
	Color primaryColor;
	Color secondaryColor;
	Color letteringColor;
	Color specialColor; //color of the center and corners
	private JButton[][] boardSquares;
	private JPanel board;

	public GameBoard(int size, int[] pc, int[] sc, int[] lc, int[] spc) {
		gridSize = size;
		primaryColor = new Color(pc[0], pc[1], pc[2]);
		secondaryColor = new Color(sc[0], sc[1], sc[2]);
		letteringColor = new Color(lc[0], lc[1], lc[2]);
		specialColor = new Color(spc[0], spc[1], spc[2]);
		initializeGUI();
	}

	public boolean initializeGUI() {
		board = new JPanel(new GridLayout(gridSize, gridSize));
		board.setBorder(new LineBorder(Color.BLACK));
		boardSquares = new JButton[gridSize][gridSize];

		String[][] pieceLocations = setStartingPieces();

		Insets buttonMargin = new Insets(0,0,0,0);
        for (int i = 0; i < boardSquares.length; i++) {
            for (int j = 0; j < boardSquares[i].length; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                b.setPreferredSize(new Dimension(64, 64));

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

	public JPanel getBoard(){
		return board;
	}

	//If there is a piece in this location it adds it to the button
	private void addPiece(String pieceName, JButton button) {
		if (pieceName.equals("black")) {
			//ImageIcon icon = new ImageIcon(getClass().getResource("/src/main/java/copenhagen/images/blackpiece.png"));
			//button.setIcon(icon);
		} else if (pieceName.equals("white")) {
			//ImageIcon icon = new ImageIcon(getClass().getResource("./images/whitepiece.png"));
			//button.setIcon(icon);
		} else if (pieceName.equals("king")) {
			//ImageIcon icon = new ImageIcon(getClass().getResource("./images/king.png"));
			//button.setIcon(icon);
		}
	}

	//Sets the location of each starting piece
	private String[][] setStartingPieces() {
		String[][] s = new String [gridSize][gridSize];
		//Initialize to empty String
		for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
				s[i][j] = "";
			}
		}

		if (gridSize==11) {
			s[0][3] = s[0][4] = s[0][5] = s[0][6] = s[0][7] = s[1][5] = "black";
			s[3][0] = s[4][0] = s[5][0] = s[6][0] = s[7][0] = s[5][1] = "black";
			s[10][3] = s[10][4] = s[10][5] = s[10][6] = s[10][7] = s[9][5] = "black";
			s[3][10] = s[4][10] = s[5][10] = s[6][10] = s[7][10] = s[5][9] = "black";

			s[3][5] = s[4][4] = s[4][5] = s[4][6] = s[5][3] = s[5][4] = "white";
			s[5][6] = s[5][7] = s[6][4] = s[6][5] = s[6][6] = s[7][5] = "white";

			s[5][5] = "king";
		}
		return s;
	}
}

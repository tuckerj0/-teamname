import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class hnefatafl{

	private static JFrame frame;
	private static JPanel board;
	private static JPanel side;
	private static int boardSize = 11;
	private static int[] primaryColor = {244,164,96};
	private static int[] secondaryColor = {139,69,19};
	private static int[] letteringColor = {0,0,0};
	private static int[] specialColor = {88,0,0};

	public static void main(String[] args){

		setUpGameBoard();

		/*Initialize JFrame. This will hold 3 JPanels*/
		frame = new JFrame("Hnefatafl");
		/* Add Menu bar at top of JFrame*/
		setUpMenu(frame);
		/*Add Board to lefthand side of JFrame*/
		frame.add(board, BorderLayout.LINE_START);
		frame.add(side, BorderLayout.EAST);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	}

	public static void setUpGameBoard(){
		gameBoard hBoard = new gameBoard(boardSize, primaryColor, secondaryColor, letteringColor, specialColor);
		board = hBoard.getBoard();
		sideBar sBar = new sideBar(primaryColor, secondaryColor, letteringColor);
		side = sBar.getSideBar();
	}

    public static void setUpMenu(JFrame frame){
        menuBar menubar = new menuBar(frame);
    }

}

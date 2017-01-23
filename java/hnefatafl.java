import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class hnefatafl{
	
	private static JFrame frame;
	private static JPanel board;
	private static int boardSize = 9;
	private static int[] primaryColor = {255,228,225};
	private static int[] secondaryColor = {143,188,143};
	private static int[]letteringColor = {0,0,0};
	
	public static void main(String[] args){
		
		setUpGameBoard();
		
		/*Initialize JFrame. This will hold 3 JPanels*/
		frame = new JFrame("Hnefatafl");
		/* Add Menu bar at top of JFrame*/
		setUpMenu(frame);
		/*Add Board to lefthand side of JFrame*/
		frame.add(board, BorderLayout.LINE_START);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	}
	public static void setUpGameBoard(){
		gameBoard hBoard = new gameBoard(boardSize, primaryColor, secondaryColor, letteringColor);
		board = hBoard.getBoard();
	}
        public static void setUpMenu(JFrame frame){
            menuBar menubar = new menuBar(frame);

        }
        
}

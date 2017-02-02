package copenhagen;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Hnefatafl {
	private static int choice;
	private static JFrame frame;
	private static JPanel board;
	private static JPanel side;
	private static JMenuBar menuBar;
	private static int boardSize = 11;
	private static int turnCount = 0;
	private static char turn = 'b';
	private static int[] primaryColor = {244,164,96};
	private static int[] secondaryColor = {139,69,19};
	private static int[] letteringColor = {0,0,0};
	private static int[] specialColor = {88,0,0};

	public static void main(String[] args) {
		MainMenu start = new MainMenu();
		while (choice == 0) {
			try {
				choice = start.getChoice();
				TimeUnit.MILLISECONDS.sleep(1);
			}
			catch(InterruptedException e) {
				System.out.println("Something went wrong. Please try running the program again.");
				System.exit(1);
			}
		}
		if (choice == 1) {
			setUpGameBoard();
			displayGameBoard();
			playGame();
		}
	}

	public static void setUpGameBoard() {
		GameBoard hBoard = new GameBoard(boardSize, primaryColor, secondaryColor, letteringColor, specialColor);
		board = hBoard.getBoard();
		SideBar sBar = new SideBar(primaryColor, secondaryColor, letteringColor);
		side = sBar.getSideBar();
        	MenuBar menu = new MenuBar();
        	menuBar = menu.getMenuBar();
	}

	public static void displayGameBoard() {
		/*Initialize JFrame. This will hold 3 JPanels*/
		frame = new JFrame("Hnefatafl");
		/*Add Board to lefthand side of JFrame*/
		frame.add(board, BorderLayout.LINE_START);
		frame.add(side, BorderLayout.EAST);
		/* Add Menu bar at top of JFrame*/
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	}
	/**
	*
	*/
	public static void playGame(){
	
	}
	/**
	*Saves present game to save file.
	*
	*@param String ame of file
	*@return true if successful
	*/
	public static boolean saveGame(String fileName){
		PrintWriter writer = null;
		try{
			writer = new PrintWriter(""+fileName+".hnf", "UTF-8");
			writer.println("hnefatafl one");
			writer.println("hnefatafl two");
		} catch (IOException e) {
		   return false;
		}
		finally{
			try{
				if(writer != null){
					writer.close();
				}
			}catch (Exception ex) {
				return false;
			}
		}
		return true;
	}
	/**
	*Loads game from file. Must have .hfn extension.
	*returns int representing status code. 0 is success, 1 is failure,
	*2 is failure due to incorrect extension.
	*
	*@param Strign name of file
	*@return int representing status code.
	*/
	public static int loadGame(String fileName){
		BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String currentLine;
			br = new BufferedReader(new FileReader(fileName));
			while ((currentLine = br.readLine()) != null) {
				System.out.println(currentLine);
			}
		} catch (IOException e) {
			return 1;
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				return 1;
			}
		}
		return 1;
	}
}

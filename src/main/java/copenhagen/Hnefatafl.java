package copenhagen;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import javax.swing.filechooser.*;
import java.awt.event.*;
import javax.imageio.ImageIO;

public class Hnefatafl {
	private static int choice;
	private static JFrame frame;
	private static JPanel board;
	private static JPanel side;
	private static JMenuBar menuBar;
	private static char[][] pieceLayout;
	private static int boardSize = 11;
	private static int turnCount = 0;
	private static char turn = 'b';
	private static GameBoard hBoard;
	private static int[] primaryColor = {244,164,96};
	private static int[] secondaryColor = {139,69,19};
	private static int[] letteringColor = {0,0,0};
	private static int[] specialColor = {0,0,88};
	private static int[] selectedLoc = {-1,-1};
	private static JButton selected;

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
		hBoard = new GameBoard(boardSize, primaryColor, secondaryColor, letteringColor, specialColor);
		board = hBoard.getBoard();
		pieceLayout = hBoard.getPieceLocations();
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
	}
	/**
	*
	*/
	public static void playGame(){
	}

	/**called whenever a square is clicked on the board
	*@param c column of square clicked
	*@param r row of square clicked
	**/
	public static void squareClicked(int c, int r, JButton clickedOn){
		unselectLast();
		if(c != selectedLoc[0] || r != selectedLoc[1]){
			selectNew(clickedOn,c,r);
		}
	}

	public static void unselectLast(){
		if(selectedLoc[0] == -1){
			return;
		}
		char piece = pieceLayout[selectedLoc[0]][selectedLoc[1]];
		try {
			Image img;
			ImageIcon icon;
			if(piece == 'b'){
				img = ImageIO.read(Hnefatafl.class.getResource("images/blackpiece.png"));
				icon = new ImageIcon(img);
				selected.setIcon(icon);
			}else if(piece == 'w'){
				img = ImageIO.read(Hnefatafl.class.getResource("images/whitePiece.png"));
				icon = new ImageIcon(img);
				selected.setIcon(icon);
			}else if(piece == 'k'){
				img = ImageIO.read(Hnefatafl.class.getResource("images/king.png"));
				icon = new ImageIcon(img);
				selected.setIcon(icon);
			}
		} catch (IOException e) {
			System.out.println("Image Didn't Load");
			System.exit(1);
		}
	}

	public static void selectNew(JButton clickedOn,int c, int r){
		char piece = pieceLayout[c][r];
		try {
			selectedLoc[0] = c;
			selectedLoc[1] = r;
			selected = clickedOn;
			Image img;
			ImageIcon icon;
			if(piece == 'b'){
				img = ImageIO.read(Hnefatafl.class.getResource("images/blackpieceSelected.png"));
				icon = new ImageIcon(img);
				clickedOn.setIcon(icon);
			}else if(piece == 'w'){
				img = ImageIO.read(Hnefatafl.class.getResource("images/whitepieceSelected.png"));
				icon = new ImageIcon(img);
				clickedOn.setIcon(icon);
			}else if(piece == 'k'){
				img = ImageIO.read(Hnefatafl.class.getResource("images/kingSelected.png"));
				icon = new ImageIcon(img);
				clickedOn.setIcon(icon);
			}else{
				selectedLoc[0] = -1;
				selectedLoc[1] = -1;
			}
		} catch (IOException e) {
			System.out.println("Image Didn't Load");
			System.exit(1);
		}
	}

	/**
	*Saves present game to save file.
	*
	*@param void
	*@return true if successful
	*/
	public static boolean saveGame(){
		PrintWriter writer = null;
		File game = null;
		try{

			JFrame parentFrame = new JFrame();

			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("hnef","hnef");
			fileChooser.setFileFilter(filter);
			fileChooser.setDialogTitle("Save file");
			int userSelection = fileChooser.showSaveDialog(parentFrame);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				game = fileChooser.getSelectedFile();
				System.out.println("Save as file: " + game.getAbsolutePath());
				writer = new PrintWriter(game, "UTF-8");
				writer.println(turnCount);
				writer.println(turn);
				char[][] layout = hBoard.getPieceLocations();
				int size = hBoard.getGridSize();
				for(int i = 0; i < size; i++){
					for(int j = 0; j < size; j++){
						writer.print(layout[i][j]);
					}
				}
			}
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
	*Loads game from file. Must have .hnef extension.Validates game file
	*is legal.
	*returns int representing status code. 0 is success, 1 is failure,
	*2 is failure due to incorrect extension.
	*
	*@param String name of file
	*@return int representing status code.
	*/
	public static int loadGame(){
		BufferedReader br = null;
		FileReader fr = null;
		File fileName = null;
		int savedTurnCount;
		char savedCurrentTurn;
		String savedLayout;
		try {
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("hnef","hnef");
			fileChooser.setFileFilter(filter);
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				fileName = fileChooser.getSelectedFile();
			}
			String extension = ""; 									// checking file extension. Must be .hnef
			String name = fileName.toString();
			int i = name.lastIndexOf('.');
			if (i > 0) {
				extension = name.substring(i + 1);
			}
			if(!extension.equals("hnef")){						// checking file extension. Must be .hnef
				return 2;
			}
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String currentLine;
			br = new BufferedReader(new FileReader(fileName));
			i = 0;
			while ((currentLine = br.readLine()) != null) {
				if(i == 0){
					savedTurnCount = Integer.parseInt(currentLine);
				}
				else if(i == 1){
					savedCurrentTurn = currentLine.charAt(0);
				}
				else if(i == 2){
					savedLayout = currentLine;
				}
				i++;
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
		return 0;
	}

}

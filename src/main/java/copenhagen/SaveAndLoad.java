package copenhagen;

import javax.swing.*;
import java.io.*;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import javax.swing.filechooser.*;

/**
 * This class deals with all logic when it comes to saving and loading game files.
 */
public class SaveAndLoad {
	private static char attackers = 'b';
	private static char defenders = 'w';
	private static char king = 'k';
	private static char empty = '0';
	private static char restricted = 'c';

    /**
     * This function saves the present game state to a save file.
     * @param size This parameter is the size of the board.
     * @param turn This parameter is whose turn it currently is.
     * @param turnCount This parameter is the total amount of turns.
     * @return This function will return true if successful or false in the case of an IOException.
     */
	public boolean save(int size, char turn, int turnCount, String aClock, String dClock){
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
				String filename = game.toString();

				if(!filename.endsWith(".hnef")){
					game =  new File(filename + ".hnef");
				}


				System.out.println("Save as file: " + game.getAbsolutePath());
				writer = new PrintWriter(game, "UTF-8");
				writer.println(turnCount);
				writer.println(turn);
				writer.println(Hnefatafl.getAttackColor());
				writer.println(Hnefatafl.getDefenseColor());
				writer.println(aClock);
				writer.println(dClock);
				for(int i = 0; i < size; i++){
					for(int j = 0; j < size; j++){
						writer.print(GameLogic.gameBoardArray[i][j]);
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
     * This function loads a game state from a file and validates it.
     * It must have a .hnef extension to be accepted.
     * @return The file containing game state
     */
	public File load() {
		BufferedReader br = null;
		FileReader fr = null;
		File fileName = null;
		int savedTurnCount = -1;
		char savedCurrentTurn = 'n';
		String savedLayout = "";
		String aTime = "";
		String dTime = "";
		try {
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("hnef","hnef");
			fileChooser.setFileFilter(filter);
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				fileName = fileChooser.getSelectedFile();
			}
			else {
			    return null;
            }
			String extension = "";
			String name = fileName.toString();
			int i = name.lastIndexOf('.');
			if (i > 0) {
				extension = name.substring(i + 1);
			}
			if(!extension.equals("hnef")){						// checking file extension. Must be .hnef
                JOptionPane.showMessageDialog(null, "Invalid save file.");
                return null;
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
					String savedAttackColor = String.valueOf(currentLine);
					Hnefatafl.setAttackColor(savedAttackColor);
				}
				else if(i == 3){
					String savedDefenseColor = String.valueOf(currentLine);
					Hnefatafl.setDefenseColor(savedDefenseColor);
				}
				else if(i == 4){
					aTime = currentLine;
				}
				else if(i == 5){
					dTime = currentLine;
				}
				else if(i == 6){
					savedLayout = currentLine;
				}
				i++;
			}
		} catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Invalid save file.");
            return null;
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Invalid save file.");
                return null;
			}
		}
		if(checkState(savedLayout, savedCurrentTurn, savedTurnCount, aTime, dTime) == true){
			return fileName;
		}
		else{
            JOptionPane.showMessageDialog(null, "Invalid save file.");
            return null;
		}
	}

    /**
     * This This function checks and validates the game state.
     * @param layout This parameter is the layout of the board and where all pieces currently reside.
     * @param turn This parameter is whose turn it currently is.
     * @param turnCount This parameter is the total amount of turns.
     * @return This function will return true if it is a valid game state. Otherwise, false if it is not.
     */
	public static boolean checkState(String layout, char turn, int turnCount, String aTime, String dTime){
		//check size of the board
		int size = layout.length();
		if(size/9 == 9 ){
			size = 9;
		}
		else if(size/11 == 11){
			size = 11;
		}
		else{
			return false;
		}
		char[][] pieces = new char[size][size];
		//check turnCount
		if(turnCount < 1){
			return false;
		}

		//check turn
		if(turn == attackers || turn == defenders);
		else{
			return false;
		}

		//count pieces
		int b = 0;
		int w = 0;
		int k = 0;
		int c = 0;
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				pieces[i][j] = layout.charAt((i*size)+j);
				if(pieces[i][j] == attackers){
					b++;
				}
				else if(pieces[i][j] == defenders){
					w++;
				}
				else if(pieces[i][j] == king){
					k++;
				}
				else if(pieces[i][j] == restricted){
					c++;
				}
				else if(pieces[i][j] == empty);
				else{
					return false;
				}
			}
		}

		//check corner tiles
		if(c != 5 && c != 4){
			return false;
		}
		//check king
		if(k != 1){
			return false;
		}
		//check black
		if(size == 9){
			if(b < 1 || b > 16){
				return false;
			}
		}
		else if(size == 11){
			if(b < 1 || b > 24){
				return false;
			}
		}
		//check white
		if(size == 9){
			if(w > 8){
				return false;
			}
		}
		else if(size == 11){
			if(w > 12){
				return false;
			}
		}
		
		int hours = Integer.parseInt(aTime.substring(0,2));
		int minutes = Integer.parseInt(aTime.substring(3,5));
		int seconds = Integer.parseInt(aTime.substring(6,8));
		if(hours < 0 && minutes < 0 && seconds < 0){
			return false;
		}
		hours = Integer.parseInt(dTime.substring(0,2));
		minutes = Integer.parseInt(dTime.substring(3,5));
		seconds = Integer.parseInt(dTime.substring(6,8));
		if(hours < 0 && minutes < 0 && seconds < 0){
			return false;
		}

		GameLogic.gameBoardArray = pieces;
		Hnefatafl.changeTimes(aTime, dTime);
		Hnefatafl.setTurn(turn);
		Hnefatafl.setTurnCount(turnCount);
		return true;
	}
}

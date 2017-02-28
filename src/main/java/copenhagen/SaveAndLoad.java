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

    /**
     * This function saves the present game state to a save file.
     * @param size This parameter is the size of the board.
     * @param layout This parameter is the layout of the board and where all pieces currently reside.
     * @param turn This parameter is whose turn it currently is.
     * @param turnCount This parameter is the total amount of turns.
     * @return This function will return true if successful or false in the case of an IOException.
     */
	public static boolean save(int size, char[][] layout, char turn, int turnCount){
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
     * This function loads a game state from a file and validates it.
     * It must have a .hnef extension to be accepted.
     * @return The file containing game state
     */
	public static File load(){
		BufferedReader br = null;
		FileReader fr = null;
		File fileName = null;
		int savedTurnCount = -1;
		char savedCurrentTurn = 'n';
		String savedLayout = "";
		try {
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("hnef","hnef");
			fileChooser.setFileFilter(filter);
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				fileName = fileChooser.getSelectedFile();
			}
			String extension = ""; 									
			String name = fileName.toString();
			int i = name.lastIndexOf('.');
			if (i > 0) {
				extension = name.substring(i + 1);
			}
			if(!extension.equals("hnef")){						// checking file extension. Must be .hnef
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
					savedLayout = currentLine;
				}
				i++;
			}
		} catch (IOException e) {
			return null;
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				return null;
			}
		}
		if(checkState(savedLayout, savedCurrentTurn, savedTurnCount) == true){
			return fileName;
		}
		else{
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
	public static boolean checkState(String layout, char turn, int turnCount){
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
		if(turn == 'b' || turn == 'w');
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
				if(pieces[i][j] == 'b'){
					b++;
				}
				else if(pieces[i][j] == 'w'){
					w++;
				}
				else if(pieces[i][j] == 'k'){
					k++;
				}
				else if(pieces[i][j] == 'c'){
					c++;
				}
				else if(pieces[i][j] == '0');
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
			if(w < 1 || w > 8){
				return false;
			}
		}
		else if(size == 11){
			if(w < 1 || w > 12){
				return false;
			}
		}
		
		return true;
	}
}
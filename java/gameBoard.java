import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.swing.border.*;

public class gameBoard{
	
	int gridSize;
	Color primaryColor;
	Color secondaryColor;
	Color letteringColor;
	private JButton[][] boardSquares;
	private JPanel board;
	
	public gameBoard(int size, int[] pc, int[] sc, int[] lc){
		gridSize = size;
		primaryColor = new Color(pc[0], pc[1], pc[2]);
		secondaryColor = new Color(sc[0], sc[1], sc[2]);
		letteringColor = new Color(lc[0], lc[1], lc[2]);
		initializeGUI();
	}
	
	public boolean initializeGUI(){
		board = new JPanel(new GridLayout(gridSize, gridSize));
		board.setBorder(new LineBorder(Color.BLACK));
		boardSquares = new JButton[gridSize][gridSize];
		
		Insets buttonMargin = new Insets(0,0,0,0);
        for (int i = 0; i < boardSquares.length; i++) {
            for (int j = 0; j < boardSquares[i].length; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
 
                ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((j % 2 == 1 && i % 2 == 1)|| (j % 2 == 0 && i % 2 == 0)) {
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
}
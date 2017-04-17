package copenhagen;

import java.awt.*;
import javax.swing.*;

/**
 * This class is used for displaying the options a user has after a game of hnefatafl has finished.
 */
public class FinalMenu {
    private static char attackers = 'b';
	private static char defenders = 'w';

    /**
     * This function calls a function to create the final menu.
     * @param winningSide This parameter represents which side won as a character.
     */
	public FinalMenu(char winningSide) {
		createMenu(winningSide);
	}

    /**
     * This function creates and displays the final menu after a game has finished.
     * @param winningSide This parameter represents which side won as a character.
     */
	private void createMenu(char winningSide) {
        BottomBar.getTimer().stopCountDown();
        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());
        content.setPreferredSize(new Dimension(250, 250));
        int fontSize = 40;
        JLabel [] winnerText;

		if (winningSide == defenders) {
            winnerText = createLabel("DEFENDERS"+"<br>"+"WIN", Hnefatafl.getDefenderImage());
		} else if (winningSide == attackers){
			winnerText = createLabel("ATTACKERS"+"<br>"+"WIN", Hnefatafl.getAttackerImage());
        } else {
            String winMessage = "DRAW!"+"<br>"+"<br>"+"<font size=\"-2\">Fifty(50) moves were made"+"<br>"+"without a capture"+"<br>"+"<br>"+"<br>"+"<font size=\"+3\">Nobody WINS</font>";
            winnerText = createLabel(winMessage, null);
        }
		winnerText[0].setFont(new Font("Courier", Font.PLAIN, fontSize));

        content.add(displayPicturesAndCaption(winnerText),createGridBagConstraints(0, 0));
        Object[] options = {"Quit","New Game"};
        int n = JOptionPane.showOptionDialog(null, content, "Hnefatafl",
                                      JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (n == 1) {
            Hnefatafl.removeOldGameBoard();
            Settings.resetTimerDefaults();
            new MainMenu();
        }
        if (n == 0) {
            System.exit(0);
        }
	}

    /**
     * This function creates the final message labels that will be displayed at the end of a game.
     * If the game does not end in a draw, the winning side's piece is displayed.
     * @param text This parameter is the message that will be displayed
     * @param img This parameter is the image icon of the winning side
     */
    private JLabel[] createLabel(String text, ImageIcon img) {
        if(img == null) {
            return(new JLabel[] {new JLabel(("<html><div style='text-align: center;'>" + text + "<br>" +"</div></html>"))});
        } else {
            return(new JLabel[] {new JLabel(("<html><div style='text-align: center;'>" + text + "<br>" +"</div></html>")), new JLabel(img, SwingConstants.CENTER)});
        }
    }

    /**
     * This function creates the necessary GridBagConstraints for putting things within a GridBagLayout panel.
     * @param x This parameter is the value for gridx attribute of the GridBagConstraints variable.
     * @param y This parameter is the value for gridy attribute of the GridBagConstraints variable.
     * @return This function returns the newly created GridBagConstraints object.
     */
    public static GridBagConstraints createGridBagConstraints(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = x;
        c.gridy = y;
        c.weightx = 0;
        c.weighty = 0;
        return c;
    }

    /**
     * This function creates a GridBagLayout JPanel that contains the given picture(s) and captions.
     * @param items This parameter is an array of JLabels which contains the picture(s) and the caption associated with
     *              it.
     * @return This function returns the created JPanel which will go into the bigger overall JPanel.
     */
    public static JPanel displayPicturesAndCaption(JLabel[] items) {
        int x = 0;
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        for (int y = 0; y < items.length; y++) {
            panel.add(items[y], createGridBagConstraints(x, y));
        }
        return panel;
    }
}

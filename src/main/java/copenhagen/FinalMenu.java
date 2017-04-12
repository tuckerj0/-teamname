package copenhagen;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used for displaying the options a user has after a game of hnefatafl has finished.
 */
public class FinalMenu {
	private JFrame menuFrame;
	private JPanel menu;
	private JPanel buttonPanel;
	private JButton newGameBtn;
	private JButton mainMenuBtn;
	private JLabel text;
	private JLabel winnerText;
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
        
		String winner;
		if (winningSide == defenders) {
			winner = "DEFENDERS";
		} else {
			winner = "ATTACKERS";
		}

		menuFrame = new JFrame();
		menuFrame.setSize(new Dimension(400, 200));

		menu = new JPanel();
		buttonPanel = new JPanel();

		text = new JLabel("Game Over!\n\n");
		text.setFont(new Font("Courier", Font.PLAIN, 30));
		winnerText = new JLabel(winner + " WIN\n\n");
		winnerText.setFont(new Font("Courier", Font.PLAIN, 40));

		addButtons();

		menu.add(text, BorderLayout.CENTER);
		menu.add(winnerText, BorderLayout.CENTER);
		menu.add(buttonPanel, BorderLayout.SOUTH);
		menuFrame.add(menu, BorderLayout.CENTER);

		showMenu();
	}

    /**
     * This methods displays the final menu to the user.
     */
	private void showMenu() {
		menuFrame.setLocationRelativeTo(null);
		menuFrame.setVisible(true);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

    /**
     * This method adds the various buttons to the final menu.
     */
	private void addButtons() {
		newGameBtn = new JButton("New Game");
		newGameBtn.addActionListener(new newListener());

		mainMenuBtn = new JButton("Exit Game");
		mainMenuBtn.addActionListener(new mainMenuListener());

		buttonPanel.add(newGameBtn);
		buttonPanel.add(mainMenuBtn);
	}

	/**
	 * This is a button listener for when the new game button is clicked. It will prompt the user to confirm.
	 * If the user confirms, it will class a new game function to reset the board and begin a new game.
	 */
	private class newListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object[] options = {"Confirm", "Cancel"};
			int n = JOptionPane.showOptionDialog(new JFrame(), "Are you sure you want to begin a new game?", "Hnefatafl", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			if (n == 0) {
                Hnefatafl.removeOldGameBoard();
                Settings.setDefaults();
                menuFrame.dispose();
				new MainMenu();
			}
			if (n == 1) {
				return;
			}

		}
	}

	/**
	 * This is a button listener for when the main menu button is clicked. It will prompt the user to confirm.
	 * If the user confirms, it will class a new game function to reset the board and begin a new game.
	 */
	private class mainMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object[] options = {"Confirm", "Cancel"};
			int n = JOptionPane.showOptionDialog(new JFrame(), "Are you sure you want to exit?", "Hnefatafl", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			if (n == 0) {
				System.exit(0);
			}
			if (n == 1) {
				return;
			}

		}
	}
}

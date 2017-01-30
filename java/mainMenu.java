import javax.swing.*;
import javax.awt.*;
import java.awt.event.*;

// load a saved game, access the rules, and change the settings

public class mainMenu {
    JFrame _mainMenuFrame = new JFrame("Main Menu"); // creates frame/window
    JPanel _mainMenuPanel = new JPanel();
    JButton _loadGameButton = new JButton("Load Saved Game");
    JButton _howToPlayButton =  new JButton("How to Play");
    JButton _settingsButton = new JButton("Settings");

    public mainMenu() {
    	_mainMenuFrame.setSize(400, 400); // width, height
    	_mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // what happens when the user closes the window
        // see Window Constants in https://docs.oracle.com/javase/8/docs/api/javax/swing/JFrame.html for more options

    	_mainMenuPanel.setLayout(new FlowLayout());  // this only needs one button, so layout doesn't matter
        // The FlowLayout will just position the elements one after another, like letters in a line of text.

    	// // May not need this
    	// _frame.add(_mainMenuPanel, BorderLayout.NORTH);

        _loadGameButton.addActionListener(new loadGameListener());
        _howToPlayButton.addActionListener(new howToPlayListener());
        _settingsButton.addActionListener(new settingsListener());
    	_loadGameButton.setFont(new Font("Courier", Font.PLAIN, 48));
        _howToPlayButton.setFont(new Font("Courier", Font.PLAIN, 48));
        _settingsButton.setFont(new Font("Courier", Font.PLAIN, 48));
        _mainMenuPanel.add(_loadGameButton);
        _mainMenuPanel.add(_howToPlayButton);
        _mainMenuPanel.add(_settingsButton);

        // Refresh window - otherwise we will not be able to see it
        // This should be the last line of the constructor
    	_frame.setVisible(true);
    }

    public static void main(String[] args) {
        new TicTacToe();
    }

    class loadGameListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
            // TODO:  Add functionality
    	}
    }

    class howToPlayListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // TODO:  Add functionality
        }
    }

    class settingsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // TODO:  Add functionality
        }
    }
}

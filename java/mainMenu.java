import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// load a saved game, access the rules, and change the settings

public class mainMenu {
    private JFrame _mainMenuFrame = new JFrame("Hnefatafl"); // creates frame/window
    private JPanel _mainMenuPanel = new JPanel();
    private JButton _playerVsButton;
    private JButton _loadGameButton = new JButton("Load Saved Game");
    private JButton _howToPlayButton =  new JButton("How to Play");
    private JButton _settingsButton = new JButton("Settings");
    int choice;

    public mainMenu() {
    	_mainMenuFrame.setSize(400, 400); // width, height
    	_mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // what happens when the user closes the window
        // see Window Constants in https://docs.oracle.com/javase/8/docs/api/javax/swing/JFrame.html for more options

    	_mainMenuPanel.setLayout(new FlowLayout());  // this only needs one button, so layout doesn't matter
        // The FlowLayout will just position the elements one after another, like letters in a line of text.
    	_mainMenuFrame.add(_mainMenuPanel);
        _playerVsButton = new JButton("Player v. Player");
        _playerVsButton.addActionListener(new newGameListener());
        _loadGameButton.addActionListener(new loadGameListener());
        _howToPlayButton.addActionListener(new howToPlayListener());
        _settingsButton.addActionListener(new settingsListener());
        _playerVsButton.setFont(new Font("Courier", Font.PLAIN, 30));
    	_loadGameButton.setFont(new Font("Courier", Font.PLAIN, 30));
        _howToPlayButton.setFont(new Font("Courier", Font.PLAIN, 30));
        _settingsButton.setFont(new Font("Courier", Font.PLAIN, 30));
        _mainMenuPanel.add(_playerVsButton);
        _mainMenuPanel.add(_loadGameButton);
        _mainMenuPanel.add(_howToPlayButton);
        _mainMenuPanel.add(_settingsButton);

        // Refresh window - otherwise we will not be able to see it
        // This should be the last line of the constructor
    	_mainMenuFrame.setVisible(true);
    }

    private class newGameListener implements  ActionListener {
        public void actionPerformed(ActionEvent e) {
            choice = 1;
        }
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
    public int getChoice() {
        return choice;
    }
}

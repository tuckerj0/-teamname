package copenhagen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This is the first thing that gets run when the program starts and allows the user to start a new game, load a saved,
 * access the rules of the game, or change the settings.
 */
public class MainMenu {
    private JFrame _mainMenuFrame = new JFrame("Hnefatafl");
    private JPanel _mainMenuPanel = new JPanel();
    private JButton _playerVsButton = new JButton("START GAME!");
    private JButton _loadGameButton = new JButton("Load Saved Game");
    private JButton _howToPlayButton =  new JButton("How to Play");
    private JButton _settingsButton = new JButton("Settings (Not yet working)");


    /**
     * This is called once the program starts and creates the main menu allowing the user to choose what they want to do.
     */
    public MainMenu() {
    	_mainMenuFrame.setSize(400, 400);
    	_mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	_mainMenuPanel.setLayout(new FlowLayout());
    	_mainMenuFrame.add(_mainMenuPanel);

        // adding the buttons that were created earlier
        _playerVsButton.addActionListener(new newGameListener());
        _loadGameButton.addActionListener(new loadGameListener());
        _howToPlayButton.addActionListener(new howToPlayListener());
        _settingsButton.addActionListener(new settingsListener());
        _playerVsButton.setFont(new Font("Courier", Font.PLAIN, 40));
    	_loadGameButton.setFont(new Font("Courier", Font.PLAIN, 40));
        _howToPlayButton.setFont(new Font("Courier", Font.PLAIN, 40));
        _settingsButton.setFont(new Font("Courier", Font.PLAIN, 40));
        _mainMenuPanel.add(_playerVsButton);
        _mainMenuPanel.add(_loadGameButton);
        _mainMenuPanel.add(_howToPlayButton);
        _mainMenuPanel.add(_settingsButton);

        _mainMenuFrame.setLocationRelativeTo(null);
    	_mainMenuFrame.setVisible(true);
    }

    /**
     * This is a button listener for when the _playerVsButton is clicked and will close the main menu and start a new
     * game.
     */
    private class newGameListener implements  ActionListener {
        public void actionPerformed(ActionEvent e) {
            _mainMenuFrame.dispose();
            new BlackWhiteStart();
        }
    }

    /**
     * This is a button listener for when the _loadGameButton is clicked and will prompt the user to load his or her
     * save file.
     */
    class loadGameListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
            boolean successfulLoad = Hnefatafl.loadGame(new SaveAndLoad());
            if (successfulLoad) {
                _mainMenuFrame.dispose();
                Hnefatafl.setUpGameBoard();
                Hnefatafl.displayGameBoard();
            }
    	}
    }

    /**
     * This is a button listener for when the _howToPlayButton is clicked and will display the rules of the game to the
     * user.
     */
    class howToPlayListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new GameRules();
        }
    }

    /**
     * This is a button listener for _settingsButton is clicked and will display the settings that can be changed.
     */
    class settingsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // TODO:  Add functionality
        }
    }
}

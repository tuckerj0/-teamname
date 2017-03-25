package copenhagen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This is the first thing that gets run when the program starts and allows the user to start a new game, load a saved,
 * access the rules of the game, or change the settings.
 */
public class MainMenu {
    private static JFrame _mainMenuFrame = new JFrame("Hnefatafl");
    private JPanel _mainMenuPanel = new JPanel();
    private JButton _playerVsButton = new JButton("START GAME!");
    private JButton _loadGameButton = new JButton("Load Saved Game");
    private JButton _howToPlayButton =  new JButton("How to Play");
    private JButton _settingsButton = new JButton("Settings");


    /**
     * This is called once the program starts and creates the main menu allowing the user to choose what they want to do.
     */
    public MainMenu() {
    	_mainMenuFrame.setSize(450, 400);
    	_mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	_mainMenuPanel.setLayout(new GridLayout(4,1));
    	_mainMenuFrame.add(_mainMenuPanel);

        // adding the buttons that were created earlier
        _playerVsButton.addActionListener(new newGameListener());
		_playerVsButton.setPreferredSize(new Dimension(450,100));
        _loadGameButton.addActionListener(new loadGameListener());
		_loadGameButton.setPreferredSize(new Dimension(450,100));
        _howToPlayButton.addActionListener(new howToPlayListener());
		_howToPlayButton.setPreferredSize(new Dimension(450,100));
        _settingsButton.addActionListener(new settingsListener());
		_settingsButton.setPreferredSize(new Dimension(450,100));
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
     * This function re-enables the JFrame after changing the settings of the game.
     */
    public static void enableFrame() {
        _mainMenuFrame.setEnabled(true);
    }

    /**
     * This is a button listener for when the _playerVsButton is clicked and will close the main menu and start a new
     * game.
     */
    private class newGameListener implements  ActionListener {
        public void actionPerformed(ActionEvent e) {
            _mainMenuFrame.dispose();
            GameLogic.setStartingPieces(Hnefatafl.getBoardSize());
            Hnefatafl.setUpGameBoard();
            Hnefatafl.displayGameBoard();            
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
            _mainMenuFrame.setEnabled(false);
            new Settings();
        }
    }
}

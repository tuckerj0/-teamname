package copenhagen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This is the class that gives the program extra functionality that allows the user to decide the color of the attackers
 * (black or white) at the start of a game.  Note: The attacker always gets the first turn.
 */
public class BlackWhiteStart {
    private JFrame _blackWhiteFrame = new JFrame("Pick a color to start the attack"); // creates frame/window
    private JPanel _blackWhitePanel = new JPanel(); // one single flow panel
    private JButton _blackButton = new JButton("Black");
    private JButton _whiteButton = new JButton("White");
    private static int boardSize = 11;


    /**
     * This function will create the JFrame that will ask the user which side they want to go first.
     */
    public BlackWhiteStart() {
        _blackWhiteFrame.setSize(400, 400); // width, height
        _blackWhiteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // what happens when the user closes the window
        // see Window Constants in https://docs.oracle.com/javase/8/docs/api/javax/swing/JFrame.html for more options

        _blackWhitePanel.setLayout(new FlowLayout());  // this only needs one button, so layout doesn't matter
        // The FlowLayout will just position the elements one after another, like letters in a line of text.

        _blackWhiteFrame.add(_blackWhitePanel);

        // adding the black button
        ActionListener blackListener = new BlackListener();
        _blackButton.addActionListener(blackListener);
        _blackButton.setFont(new Font("Courier", Font.PLAIN, 48));
        _blackWhitePanel.add(_blackButton);
        // adding the white button
        ActionListener whiteListener = new WhiteListener();
        _whiteButton.addActionListener(whiteListener);
        _whiteButton.setFont(new Font("Courier", Font.PLAIN, 48));
        _blackWhitePanel.add(_whiteButton);

        // Refresh window - otherwise we will not be able to see it
        // This should be the last line of the constructor
        _blackWhiteFrame.pack();
        _blackWhiteFrame.setLocationRelativeTo(null);
        _blackWhiteFrame.setVisible(true);
    }

    /**
     * This is a button listener for when the _blackButton button is clicked for the black pieces to start as the attackers.  This is implemented as the default state.
     */
    class BlackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Hnefatafl.blackStart();
            _blackWhiteFrame.dispose();
            GameLogic.setStartingPieces(boardSize);
            Hnefatafl.setUpGameBoard();
            Hnefatafl.displayGameBoard();
        }
    }

    /**
     * This is a button listener for when the _whiteButton button is clicked for the white pieces to start as the attackers.
     */
    class WhiteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Hnefatafl.whiteStart();
            _blackWhiteFrame.dispose();
            GameLogic.setStartingPieces(boardSize);
            Hnefatafl.setUpGameBoard();
            Hnefatafl.displayGameBoard();
        }
    }
}

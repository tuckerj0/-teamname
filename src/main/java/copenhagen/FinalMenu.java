package copenhagen;

import java.awt.*;
import javax.swing.*;

/**
 * This class is used for displaying the options a user has after a game of hnefatafl has finished.
 */
public class FinalMenu {

    private JFrame menuFrame;
    private JPanel menu;
    private JPanel buttonPanel;
    private JButton newGameBtn;
    private JButton quitGameBtn;
    private JLabel text;

    /**
     * This function calls a function to create the final menu.
     */
    public FinalMenu() {
        createMenu();
    }

    /**
     * This function creates the final menu after a game has finished.
     */
    private void createMenu() {
        menuFrame = new JFrame();
        menu = new JPanel();
        buttonPanel = new JPanel();
        menuFrame.setSize(400, 200);
        menuFrame.add(menu);
        
        text = new JLabel("Game Over");
        text.setFont(new Font("Courier", Font.PLAIN, 40));
        menu.setLayout(new FlowLayout());
        menu.add(text, BorderLayout.CENTER);  
        menu.add(buttonPanel, BorderLayout.CENTER);
        
        newGameBtn = new JButton("New Game");
        quitGameBtn = new JButton("Quit Game");
        buttonPanel.add(newGameBtn);
        buttonPanel.add(quitGameBtn);
    }

    /**
     * This function will display the created final menu to the user.
     */
    public void showMenu() {
        menuFrame.pack();
        menuFrame.setVisible(true);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

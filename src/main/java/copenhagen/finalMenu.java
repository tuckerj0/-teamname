package copenhagen;

import java.awt.*;
import javax.swing.*;

public class finalMenu {

    private JFrame menuFrame;
    private JPanel menu;
    private JPanel buttonPanel;
    private JButton newGameBtn;
    private JButton quitGameBtn;
    private JLabel text;

    public finalMenu() {
        createMenu();        

    }

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


    public void showMenu() {
        menuFrame.setVisible(true);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

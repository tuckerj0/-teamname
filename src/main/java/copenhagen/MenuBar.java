package copenhagen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * This class contains everything related to the menu bar at the top of the main program when playing a game.
 */
public class MenuBar {

    JMenuItem menuItem;
    JMenuBar menuBar;
    JMenu menu;

    /**
     * This function calls the function to set up the menu bar in the program.
     */
    public MenuBar() {
        setUpMenuBar();
    }

    /**
     * This function sets up the menu bar at the top in the main program.
     */
    public void setUpMenuBar() {
        ActionListener helpListener = new HelpListener();
        menu = new JMenu("File");
        menuBar = new JMenuBar();

        menuBar.add(menu);
        menuItem = new JMenuItem("Settings");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        menu.add(menuItem);
        menu.addSeparator();

        menu = new JMenu("Help");
        menuBar.add(menu);
        menuItem = new JMenuItem("Game Rules");
        menuItem.addActionListener(helpListener);
        menu.add(menuItem);
    }

    /**
     * This function is called once the JMenuBar is fully formed and completed.
     * @return This returns the newly created JMenuBar when the program is ran.
     */
    public JMenuBar getMenuBar() {
        return menuBar;
    }

    /**
     * This is a menu item listener for when "Game Rules" is clicked and will display the hnefatafl game rules.
     */
    class HelpListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new GameRules();
        }
    }
}

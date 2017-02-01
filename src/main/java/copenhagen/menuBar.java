package copenhagen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class menuBar {

    JPopupMenu popup;
    JMenuItem menuItem;
    JMenuBar menuBar;
    JMenu menu;

    public menuBar() {
        setUpMenuBar();
    }

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

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    class HelpListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            new gameRules();
        }
    }

}

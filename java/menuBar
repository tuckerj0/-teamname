import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SneakyPT
 */
public class menuBar implements ActionListener, ItemListener{
    JPopupMenu popup;
    JMenuItem menuItem;
    JMenuBar menuBar;
    JMenu menu;
    
    public menuBar(JFrame frame){
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
        menu.add(menuItem);
        frame.setJMenuBar(menuBar);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}

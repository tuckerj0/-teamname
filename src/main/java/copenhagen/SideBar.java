package copenhagen;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class SideBar {
    public boolean gameSaved;   // anytime a move is made in the game, set this this boolean to false
    private static final int buttonWidth = 100;
    private static final int buttonHeight = 40;
    private static final int startEndGap = 60;
    private static final int midGap = 70;
    private Color primaryColor;
    private Color secondaryColor;
    private Color letteringColor;
    private JPanel side;
    private JButton newGame;
    private JButton saveGame;
    private JButton loadGame;
    private JButton help;
    private JButton concede;
    private JButton exit;
    private JFrame exitWindow;

    public SideBar(int[] pc, int[] sc, int[] lc) {
        primaryColor = new Color(pc[0], pc[1], pc[2]);
        secondaryColor = new Color(sc[0], sc[1], sc[2]);
        letteringColor = new Color(lc[0], lc[1], lc[2]);
        createPanel();
        createButtons();
    }

    private void createPanel() {
        side = new JPanel();
        side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));
        side.setBackground(secondaryColor);
        side.add(Box.createRigidArea(new Dimension(0, startEndGap)));
    }

    private void createButtons() {
        newGame = new JButton("New Game");
        styleButton(newGame, midGap);

        saveGame = new JButton("Save Game");
        styleButton(saveGame, midGap);
        saveGame.addActionListener(new SaveListener());

        loadGame = new JButton("Load Game");
        styleButton(loadGame, midGap);
		loadGame.addActionListener(new loadListener());

        help = new JButton("Help");
        styleButton(help, midGap);
        help.addActionListener(new HelpListener());

        concede = new JButton("Forfeit");
        styleButton(concede, midGap);

        exit = new JButton("Exit");
        styleButton(exit, startEndGap);
        exit.addActionListener(new ExitListener());
    }

    private void styleButton(JButton btn, int gap) {
        btn.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        btn.setBackground(primaryColor);
        btn.setForeground(letteringColor);
        side.add(btn);
        side.add(Box.createRigidArea(new Dimension(0, gap)));
    }

    private class SaveListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {
			// TODO: save the game and ask where to save it and what to name the file if first save
			Hnefatafl.saveGame();
		}
    }
	
	private class loadListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {
           // TODO: load the game and ask where to load it from
		   Hnefatafl.loadGame();
           
       }
    }

    private class HelpListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new GameRules();
        }
    }

     private class ExitListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {
           if (gameSaved) {
               System.exit(0);
           }
           Object[] options = {"Save", "Don't Save", "Cancel"};
           int n = JOptionPane.showOptionDialog(exitWindow, "Want to save your game progress?", "Hnefatafl", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
           if (n == 0) {
               // TODO: save the game and ask where to save it and what to name the file if first save
           }
           if (n == 1) {
               System.exit(0);
           }
       }
    }

    public JPanel getSideBar() {
        return side;
    }
	
  
}

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class sideBar {
    public boolean gameSaved;   // anytime a move is made in the game, set this this boolean to false
    private static final int buttonWidth = 100;
    private static final int buttonHeight = 40;
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

    public sideBar(int[] pc, int[] sc, int[] lc) {
        primaryColor = new Color(pc[0], pc[1], pc[2]);
        secondaryColor = new Color(sc[0], sc[1], sc[2]);
        letteringColor = new Color(lc[0], lc[1], lc[2]);
        int startEndGap = 60;
        int midGap = 70;

        side = new JPanel();
        side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));
        side.setBackground(secondaryColor);
        side.add(Box.createRigidArea(new Dimension(0, startEndGap)));

        newGame = new JButton("New Game");
        newGame.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        newGame.setBackground(primaryColor);
        newGame.setForeground(letteringColor);
        side.add(newGame);
        side.add(Box.createRigidArea(new Dimension(0, midGap)));

        saveGame = new JButton("Save Game");
        saveGame.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        saveGame.setBackground(primaryColor);
        saveGame.setForeground(letteringColor);
        saveGame.addActionListener(new SaveListener());
        side.add(saveGame);
        side.add(Box.createRigidArea(new Dimension(0, midGap)));

        loadGame = new JButton("Load Game");
        loadGame.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        loadGame.setBackground(primaryColor);
        loadGame.setForeground(letteringColor);
        side.add(loadGame);
        side.add(Box.createRigidArea(new Dimension(0, midGap)));

        help = new JButton("Help");
        help.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        help.setBackground(primaryColor);
        help.setForeground(letteringColor);
        side.add(help);
        side.add(Box.createRigidArea(new Dimension(0, midGap)));

        concede = new JButton("Forfeit");
        concede.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        concede.setBackground(primaryColor);
        concede.setForeground(letteringColor);
        side.add(concede);
        side.add(Box.createRigidArea(new Dimension(0, midGap)));

        exit = new JButton("Exit");
        exit.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        exit.setBackground(primaryColor);
        exit.setForeground(letteringColor);
        exit.addActionListener(new ExitListener());
        side.add(exit);
        side.add(Box.createRigidArea(new Dimension(0, startEndGap)));

    }

    private class SaveListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {
           // TODO: save the game and ask where to save it and what to name the file if first save
           gameSaved = true;
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

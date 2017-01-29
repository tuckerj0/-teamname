import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sideBar {
    public boolean gameSaved;   // anytime a move is made in the game, set this this boolean to false
    private static final int buttonWidth = 100;
    private static final int buttonHeight = 40;
    private JPanel side;
    private JButton newGame;
    private JButton saveGame;
    private JButton loadGame;
    private JButton help;
    private JButton concede;
    private JButton exit;
    private JFrame exitWindow;

   /* public BLDComponent(float alignmentX, float hue,
                        int shortSideSize,
                        boolean restrictSize,
                        boolean printSize,
                        String name) {
        this.name = name;
        this.restrictMaximumSize = restrictSize;
        this.printSize = printSize;
        setAlignmentX(alignmentX);
        normalHue = Color.getHSBColor(hue, 0.4f, 0.85f);
        preferredSize = new Dimension(shortSideSize*2,
                shortSideSize);

        addMouseListener(this);
    }*/

   private void createButton(JButton btn, String text) {
       btn = new JButton(text);
       btn.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
       side.add(btn);
   }

    public sideBar() {
        int startEndGap = 60;
        int midGap = 70;

        side = new JPanel();
        side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));
        side.add(Box.createRigidArea(new Dimension(0, startEndGap)));

        newGame = new JButton("New Game");
        newGame.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        side.add(newGame);
        side.add(Box.createRigidArea(new Dimension(0, midGap)));

        saveGame = new JButton("Save Game");
        saveGame.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        saveGame.addActionListener(new SaveListener());
        side.add(saveGame);
        side.add(Box.createRigidArea(new Dimension(0, midGap)));

        loadGame = new JButton("Load Game");
        loadGame.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        side.add(loadGame);
        side.add(Box.createRigidArea(new Dimension(0, midGap)));

        help = new JButton("Help");
        help.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        side.add(help);
        side.add(Box.createRigidArea(new Dimension(0, midGap)));

        concede = new JButton("Forfeit");
        concede.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        side.add(concede);
        side.add(Box.createRigidArea(new Dimension(0, midGap)));

        exit = new JButton("Exit");
        exit.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
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
           int n = JOptionPane.showOptionDialog(exitWindow, "Want to save your game progress?", "Hnefatafl", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[2]);
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

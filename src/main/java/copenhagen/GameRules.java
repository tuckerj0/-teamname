package copenhagen;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

/**
 * This class is used when displaying the game rules for the game.
 */
public class GameRules {
    /**
     * This function creates the necessary GridBagConstraints for putting things within a GridBagLayout panel.
     * @param x This parameter is the value for gridx attribute of the GridBagConstraints variable.
     * @param y This parameter is the value for gridy attribute of the GridBagConstraints variable.
     * @return This function returns the newly created GridBagConstraints object.
     */
    public static GridBagConstraints createGridBagConstraints(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = x;
        c.gridy = y;
        c.weightx = 0;
        c.weighty = 0;
        return c;
    }

    /**
     * This function creates one of the rules with all of necessary attributes.
     * @param rule This parameter is the actual rule's text.
     * @return This function returns the created JTextArea that represents the given game rule.
     */
    public static JTextArea createRule(String rule) {
        JTextArea text = new JTextArea(rule);
        text.setCaretPosition(0);
        text.setEditable(false);
        text.setOpaque(false);
        text.setFocusable(false);
        return text;
    }

    /**
     * This function grabs an image and puts it into a JLabel to later then be put in the JPanel.
     * @param s This parameter is the path of where the image is located at.
     * @return This function returns a JLabel representation of the image.
     */
    public static JLabel getImage(String s) {
        try {
            BufferedImage image = ImageIO.read(Hnefatafl.class.getResource(s));
            JLabel result = new JLabel(new ImageIcon(image), SwingConstants.LEFT);
            return result;
        }
        catch (IOException e) {
            return  null;
        }
    }

    /**
     * This function creates a label for whatever picture it relates to.
     * @param caption This parameter is the actual caption's text.
     * @return This function returns the created JLabel that represents the given caption.
     */
    public static JLabel createCaption(String caption) {
        JLabel text = new JLabel(caption);
        text.setOpaque(false);
        text.setFocusable(false);
        return text;
    }

    /**
     * This function creates a GridBagLayout JPanel that contains the given picture(s) and captions.
     * @param items This parameter is an array of JLabels which contains the picture(s) and the caption associated with
     *              it.
     * @return This function returns the created JPanel which will go into the bigger overall JPanel.
     */
    public static JPanel displayPicturesAndCaption(JLabel[] items) {
        int y = 0;
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        for (int x = 0; x < items.length; x++) {
            panel.add(items[x], createGridBagConstraints(x, y));
        }
        return panel;
    }

    /**
     * This function creates the JScrollPane which will go in the JOptionPane to finally display the game rules.
     * @param panel This parameter is the JPanel filled with all the game rules and pictures with captions.
     * @return This function returns the created JScrollPane.
     */
    public static JScrollPane createJScrollPane(JPanel panel) {
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.getVerticalScrollBar().setValue(0);
        scrollPane.setPreferredSize(new Dimension(1100, 600));
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        return scrollPane;
    }

    /**
     * This will create a new JOptionPane with the Hnefatafl game rules.
     */
    public GameRules() {
        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());
        JLabel[] illustration;
        int y = 0;


        JTextArea rule1 = createRule("1.   Two players may participate. One player plays the king's side, " +
                "with a king and his defenders, and the other player plays the attackers. There are twelve " +
                "defenders and twenty-four attackers.");
        content.add(rule1, createGridBagConstraints(0, y));
        y++;


        JTextArea rule2 = createRule("\n\n2.   The game is played on a board with 11x11 squares and with " +
                "initial set-up as shown:");
        content.add(rule2, createGridBagConstraints(0, y));
        y++;
        illustration = new JLabel[] {getImage("images/rules/rule2/1.png"), createCaption("Initial set-up.")};
        content.add(displayPicturesAndCaption(illustration), createGridBagConstraints(0, y));
        y++;


        JTextArea rule3 = createRule("\n\n3.   The central square, called the throne, and the four squares in " +
                "the corners are restricted and may only be occupied by the king. It is allowed for the king to " +
                "re-enter the throne, and all pieces may \npass through the throne when it is empty. " +
                "\n   The four corner squares are hostile to all pieces, which means that they can replace one of " +
                "the two pieces taking part in a capture. " +
                "\n   The throne is always hostile to the attackers, but only hostile to the defenders when it is " +
                "empty. Five restricted squares.");
        content.add(rule3, createGridBagConstraints(0, y));
        y++;
        illustration = new JLabel[] {getImage("images/rules/rule3/1.png"), createCaption("Five restricted squares.")};
        content.add(displayPicturesAndCaption(illustration), createGridBagConstraints(0, y));
        y++;


        JTextArea rule4 = createRule("\n\n4.   The attackers' side moves first, and the game then proceeds by alternate moves."
                + " All pieces move any number of vacant squares along a row or a column, like a rook in chess.");
       content.add(rule4, createGridBagConstraints(0, y));
        y++;
        illustration = new JLabel[] {getImage("images/rules/rule4/1.png"), createCaption("Moving a piece.")};
        content.add(displayPicturesAndCaption(illustration), createGridBagConstraints(0, y));
        y++;


        JTextArea rule5a = createRule("\n\n5a.   All pieces except the king are captured if they are sandwiched " +
                "between two enemy pieces, or between an enemy piece and a hostile square, along a column or a " +
                "row. The two enemy pieces \nshould either be on the square above and below or on the square to the " +
                "left and to the right of the attacked piece. A piece is only captured if the trap is closed by a " +
                "move of the opponent, and it is, \ntherefore, allowed to move in between two enemy pieces. A " +
                "captured piece is removed from the board and is no longer active in the game. The king may take " +
                "part in captures.");
        content.add(rule5a, createGridBagConstraints(0, y));
        y++;
        illustration = new JLabel[] {getImage("images/rules/rule5/a1.png"), getImage("images/rules/rule5/a2.png"),
                getImage("images/rules/rule5/a3.png"), getImage("images/rules/rule5/a4.png"),
                getImage("images/rules/rule5/a5.png"), createCaption("Capture of pieces.")};
        content.add(displayPicturesAndCaption(illustration), createGridBagConstraints(0, y));
        y++;


        JTextArea rule5b = createRule("\n\n5b.   The shieldwall rule for capturing a row of pieces on the " +
                "board edge: A row of two or more taflmen along the board edge may be captured together, by " +
                "bracketing the whole group at both ends, as \nlong as every member of the row has an enemy taflman " +
                "directly in front of him. A corner square may stand in for one of the bracketing pieces at one end " +
                "of the row. " +
                "\n   The king may take part in the capture, either as part of the shieldwall or as a " +
                "bracketing piece. If the king plus one or more defenders are attacked with a shieldwall, the attack " +
                "will capture the \ndefenders but not the king.");
        content.add(rule5b, createGridBagConstraints(0, y));
        y++;
        illustration = new JLabel[] {getImage("images/rules/rule5/b1.png"), getImage("images/rules/rule5/b2.jpg"),
                createCaption("Shieldwall captures.")};
        content.add(displayPicturesAndCaption(illustration), createGridBagConstraints(0, y));
        y++;


        JTextArea rule6a = createRule("\n\n6a.   The objective for the king's side is to move the king to any " +
                "of the four corner squares. In that case, the king has escaped and his side wins. The king's side wins.");
        content.add(rule6a, createGridBagConstraints(0, y));
        y++;
        illustration = new JLabel[] {getImage("images/rules/rule6/a1.png"), createCaption("The king's side wins.")};
        content.add(displayPicturesAndCaption(illustration), createGridBagConstraints(0, y));
        y++;


        JTextArea rule6b = createRule("\n\n6b.   Exit forts: The defenders also win if the king has contact " +
                "with the board edge, is able to move, and it is impossible for the attackers to capture him after " +
                "any amount of moves.");
        content.add(rule6b, createGridBagConstraints(0, y));
        y++;
        illustration = new JLabel[] {getImage("images/rules/rule6/b1.png"), getImage("images/rules/rule6/b2.jpg"),
                createCaption("Exit forts.")};
        content.add(displayPicturesAndCaption(illustration), createGridBagConstraints(0, y));
        y++;


        JTextArea rule7a = createRule("\n\n7a.   The attackers win if they can capture the king before he " +
                "escapes. The king is captured when the attackers surround him in all four cardinal points. When he " +
                "is on a square next to the throne, the \nattackers must occupy all surrounding squares in the four " +
                "points of the compass except the throne.");
        content.add(rule7a, createGridBagConstraints(0, y));
        y++;
        illustration = new JLabel[] {getImage("images/rules/rule7/a1row1.png"), getImage("images/rules/rule7/a2row1.png"),
                getImage("images/rules/rule7/a3row1.png"), createCaption("The king is captured.")};
        content.add(displayPicturesAndCaption(illustration), createGridBagConstraints(0, y));
        y++;
        JTextArea blankSpace = createRule("\n");
        content.add(blankSpace, createGridBagConstraints(0, y));
        y++;
        illustration = new JLabel[] {getImage("images/rules/rule7/a1row2.png"), getImage("images/rules/rule7/a2row2.png"),
                createCaption("The king is not captured.")};
        content.add(displayPicturesAndCaption(illustration), createGridBagConstraints(0, y));
        y++;


        JTextArea rule7b = createRule("\n\n7b.   If the attackers surround the king and ALL remaining " +
                "defenders, then they win, as they have prevented the king from escaping. The attackers win by " +
                "encircling all defenders.");
        content.add(rule7b, createGridBagConstraints(0, y));
        y++;
        illustration = new JLabel[] {getImage("images/rules/rule7/b1.png"), createCaption("The king's side wins.")};
        content.add(displayPicturesAndCaption(illustration), createGridBagConstraints(0, y));
        y++;


        JTextArea rule8a = createRule("\n\n8a.   Perpetual repetitions are forbidden. A player who maintains a " +
                "board repetition (\"the aggressive player\") must find another move to break the repetition, or he " +
                "loses the game." +
                " \n   \"The aggressive player\" is the player who continually side steps with a piece in order to " +
                "find an open path to break through. The other player continually brings his piece in line with the " +
                "aggressive \npiece in order to block the open path. In other words, \"the aggressive player\" is the " +
                "one who has some choice in whether to repeat the pattern or not." +
                " \n   If a board position is repeated for the third time, \"the aggressive player\" loses the game.");
        content.add(rule8a, createGridBagConstraints(0, y));
        y++;
        illustration = new JLabel[] {getImage("images/rules/rule8/a1.png"), createCaption("Perpetual repetition - the king must find another move.")};
        content.add(displayPicturesAndCaption(illustration), createGridBagConstraints(0, y));
        y++;


        JTextArea rule8b = createRule("\n\n8b.   Draw forts are forbidden: If the defenders repeat the " +
                "defending board position three times while no piece is captured, the attackers win." +
                "\n   (Only in force when the defenders have at least king plus four men left, which is the minimum " +
                "of pieces needed to build a fort).");
        content.add(rule8b, createGridBagConstraints(0, y));
        y++;
        illustration = new JLabel[] {getImage("images/rules/rule8/b1.png"), createCaption("Draw forts forbidden.")};
        content.add(displayPicturesAndCaption(illustration), createGridBagConstraints(0, y));
        y++;


        JTextArea rule9 = createRule("\n\n9.   If a player cannot move, he loses the game.");
        content.add(rule9, createGridBagConstraints(0, y));
        y++;
        illustration = new JLabel[] {getImage("images/rules/rule9/1.png"), createCaption("The defenders cannot move and lose.")};
        content.add(displayPicturesAndCaption(illustration), createGridBagConstraints(0, y));
        y++;


        JTextArea rule10 = createRule("\n\n10.   The game is a draw if no capture has been made in the last " +
                "fifty moves (for this purpose a \"move\" consists of a player completing his turn followed by his " +
                "opponent completing his turn).\n");
        content.add(rule10, createGridBagConstraints(0, y));


        JOptionPane.showMessageDialog(null, createJScrollPane(content), "Hnefatafl Game Rules",
                JOptionPane.PLAIN_MESSAGE);
    }
}

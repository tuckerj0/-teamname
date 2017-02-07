package copenhagen;

import java.awt.*;
import javax.swing.*;

/**
 * This class is used when displaying the game rules for the game.
 */
public class GameRules {

    private JTextArea text;
    private JScrollPane scrollPane;

    /**
     * This will create a new JFrame with the hnefatafl game rules.
     */
    public GameRules() {
        setText();
        scrollPane = new JScrollPane(text);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(600, 600));
        JOptionPane.showMessageDialog(null, scrollPane, "Hnefatafl Game Rules",
                JOptionPane.PLAIN_MESSAGE);
    }

    private void setText() {
        text = new JTextArea("1.    Two players may participate. One player plays the king's side, with a king and his defenders, and the other player plays the attackers. "
                + " There are twelve defenders and twenty-four attackers.\n"
                + "\n"
                + "\n"
                + "2.   The game is played on a board with 11x11 squares and with initial set-up as shown:\n"
                + // " Initial set-up. \n" +
                "\n"
                + "3.   The central square, called the throne, and the four squares in the corners are restricted and may only be occupied by the king."
                + " It is allowed for the king to re-enter the throne, and all pieces may pass through the throne when it is empty."
                + " The four corner squares are hostile to all pieces, which means that they can replace one of the two pieces taking part in a capture."
                + " The throne is always hostile to the attackers, but only hostile to the defenders when it is empty."
                + " Five restricted squares. \n"
                + "\n"
                + "4.   The attackers' side moves first, and the game then proceeds by alternate moves."
                + " All pieces move any number of vacant squares along a row or a column, like a rook in chess."
                + "\n Moving a piece. \n"
                + "\n"
                + "5a.  All pieces except the king are captured if they are sandwiched between two enemy pieces,\n"
                + " or between an enemy piece and a hostile square, along a column or a row.\n"
                + " The two enemy pieces should either be on the square above and below or on the square to the left and to the right of the attacked piece."
                + " A piece is only captured if the trap is closed by a move of the opponent, and it is, therefore, allowed to move in between two enemy pieces."
                + " A captured piece is removed from the board and is no longer active in the game. The king may take part in captures."
                + " Capture of pieces. \n"
                + "\n The piece is not captured. \n"
                + "\n"
                + "5b.  The shieldwall rule for capturing a row of pieces on the board edge:\n"
                + " A row of two or more taflmen along the board edge may be captured together, by bracketing the whole group at both ends, as long as every member"
                + " of the row has an enemy taflman directly in front of him. A corner square may stand in for one of the bracketing pieces at one end of the row."
                + " The king may take part in the capture, either as part of the shieldwall or as a bracketing piece."
                + " If the king plus one or more defenders are attacked with a shieldwall, the attack will capture the defenders but not the king."
                + " Shieldwall captures. \n"
                + "\n"
                + "6a.  The objective for the king's side is to move the king to any of the four corner squares. In that case, the king has escaped and his side wins.\n"
                + " The king's side wins. \n"
                + "\n"
                + "6b.  Exit forts: The defenders also win if the king has contact with the board edge, is able to move,"
                + " and it is impossible for the attackers to capture him after any amount of moves.\n"
                + "  Exit forts. \n"
                + "\n"
                + "7a.  The attackers win if they can capture the king before he escapes. The king is captured when the attackers surround him in all four cardinal points."
                + " When he is on a square next to the throne, the attackers must occupy all surrounding squares in the four points of the compass except the throne."
                + "\n The king is captured. \n"
                + "\n"
                + "  The king is not captured. \n"
                + "\n"
                + "7b.  If the attackers surround the king and ALL remaining defenders, then they win, as they have prevented the king from escaping."
                + " The attackers win by encircling all defenders. \n"
                + "\n"
                + "8a.  Perpetual repetitions are forbidden. A player who maintains a board repetition (\"the aggressive player\") must find another"
                + " move to break the repetition, or he loses the game."
                + " The aggressive player\" is the player who continually side steps with a piece in order to find an open path to break through."
                + " The other player continually brings his piece in line with the aggressive piece in order to block the open path."
                + " In other words, \"the aggressive player\" is the one who has some choice in whether to repeat the pattern or not."
                + " If a board position is repeated for the third time, \"the aggressive player\" loses the game.\n"
                + "\n Perpetual repetition - the king must find another move. \n"
                + "\n"
                + "8b.  Draw forts are forbidden: If the defenders repeat the defending board position three times while no piece is captured, the attackers win."
                + " (Only in force when the defenders have at least king plus four men left, which is the minimum of pieces needed to build a fort)."
                + "\n Draw forts forbidden. \n"
                + "\n"
                + "9.   If a player cannot move, he loses the game."
                + "\n The defenders cannot move and lose. \n"
                + "\n"
                + "10.  The game is a draw if no capture has been made in the last fifty moves (for this purpose a \"move\" consists of a player"
                + " completing his turn followed by his opponent completing his turn).");
    }
}

package copenhagen;

import javax.swing.*;
import java.awt.*;

public class BottomBar {
    private Color primaryColor;
    private Color secondaryColor;
    private Color letteringColor;
    private JPanel bottom;
    private static JLabel turn;
    private JLabel turnNumber;

    public BottomBar(int[] pc, int[] sc, int[] lc) {
        primaryColor = new Color(pc[0], pc[1], pc[2]);
        secondaryColor = new Color(sc[0], sc[1], sc[2]);
        letteringColor = new Color(lc[0], lc[1], lc[2]);
        createPanel();
        createLabels();
    }

    private void createPanel() {
        bottom = new JPanel();
        bottom.setBackground(primaryColor);
    }

    private void createLabels() {
        turn = new JLabel("Turn: Attackers");
        turn.setForeground(letteringColor);
        bottom.add(turn);
    }

    public static void endTurn(char c) {
        if (c == 'b') {
            turn.setText("Turn: Defenders");
        }
        else if (c == 'w') {
            turn.setText("Turn: Attackers");
        }
    }

    public JPanel getBottomBar() {
        return bottom;
    }
}

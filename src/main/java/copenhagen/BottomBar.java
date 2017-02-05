package copenhagen;

import javax.swing.*;
import java.awt.*;

public class BottomBar {
    private Color primaryColor;
    private Color letteringColor;
    private JPanel bottom;
    private static JLabel turn;
    private static JLabel turnCount;

    public BottomBar(int[] pc, int[] lc, char start, int count) {
        primaryColor = new Color(pc[0], pc[1], pc[2]);
        letteringColor = new Color(lc[0], lc[1], lc[2]);
        createPanel();
        setTurn(start);
        setTurnCount(count);
        addLabels();
    }

    private void createPanel() {
        bottom = new JPanel();
        bottom.setBackground(primaryColor);
    }

    private void setTurn(char c) {
        if (c == 'b') {
            turn = new JLabel("Turn: Attackers");
        }
        else if (c == 'w') {
            turn = new JLabel("Turn: Defenders");
        }
        turn.setForeground(letteringColor);
    }

    private void setTurnCount(int i) {
        turnCount = new JLabel("Turn Number: " + i);
        turnCount.setForeground(letteringColor);
    }

    private void addLabels() {
        bottom.add(turn);
        bottom.add(Box.createRigidArea(new Dimension(300, 0)));
        bottom.add(turnCount);
    }

    public static void endTurn(char c, int i) {
        if (c == 'b') {
            turn.setText("Turn: Defenders");
        }
        else if (c == 'w') {
            turn.setText("Turn: Attackers");
        }
        turnCount.setText("Turn Number: " + i);
    }

    public JPanel getBottomBar() {
        return bottom;
    }
}

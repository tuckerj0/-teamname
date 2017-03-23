package copenhagen;

import javax.swing.*;
import java.awt.*;

/**
 * This class is used for anything dealing with the bottom JPanel in the main part of the game, including
 * which player has the current turn and the current turn number.
 */
public class BottomBar {
    private Color primaryColor;
    private Color letteringColor;
    private JPanel bottom;
    private static JLabel turn;
    private static JLabel turnCount;
    private static char attackers = 'b';
	private static char defenders = 'w';
	private static char king = 'k';
	private static char empty = '0';
	private static char restricted = 'c';
    private static Clock attackersClock;
    private static Clock defendersClock;
    private static JLabel attackersTime;
    private static JLabel defendersTime;
    private static CountDownTimer timer;
    private static int perMoveTime = 3;//number of seconds added to each user after their move
    private static int startingMinutes = 5;//default game set to 5 minutes
    private static int startingHours = 0;
    private static int startingSeconds = 0;

    /**
     * This is called when creating the bottom bar JPanel.
     * @param pc This denotes the color that will be used for the background the panel.
     * @param lc This denotes the color that will be used for the text of the labels.
     * @param start This denotes who goes first (black or white).
     * @param count This denotes the current turn number of the game.
     */
    public BottomBar(int[] pc, int[] lc, char start, int count) {
        primaryColor = new Color(pc[0], pc[1], pc[2]);
        letteringColor = new Color(lc[0], lc[1], lc[2]);
        createPanel();
        setTurn(start);
        setTurnCount(count);
        addLabels();

    }

    /**
     * This function creates the bottom JPanel that will contain turn information.
     */
    private void createPanel() {
        bottom = new JPanel();
        bottom.setBackground(primaryColor);
    }

    /**
     * This function displays who is starting based on the parameter given.
     * @param c This tells which person starts, attackers or defenders
     */
    private void setTurn(char c) {
        if (c == attackers) {
            turn = new JLabel("Turn: Attackers");
        }
        else if (c == defenders) {
            turn = new JLabel("Turn: Defenders");
        }
        turn.setForeground(letteringColor);
    }

    /**
     * This function sets the turn number of the game based on the parameter given.
     * @param i This tells what turn the game is currently on.
     */
    private void setTurnCount(int i) {
        turnCount = new JLabel("Turn Number: " + i);
        turnCount.setForeground(letteringColor);
    }

    /**
     * This function adds the labels to the bottom panel.
     */
    private void addLabels() {
        bottom.add(turn);
        addClocks();
        bottom.add(turnCount);
    }

    /**
     * This function adds the clocks to the bottom panel and starts the countdown timer.
     */
    private void addClocks() {
        attackersClock = new Clock(startingHours,startingMinutes,startingSeconds);
        defendersClock = new Clock(startingHours,startingMinutes,startingSeconds);
        timer = new CountDownTimer();

        attackersTime = attackersClock.formatClock("Attackers");
        defendersTime = defendersClock.formatClock("Defenders");

        bottom.add(Box.createRigidArea(new Dimension(75, 0)));
        bottom.add(attackersTime);
        bottom.add(Box.createRigidArea(new Dimension(75, 0)));
        bottom.add(defendersTime);
        bottom.add(Box.createRigidArea(new Dimension(75, 0)));

        timer.runCountdown(attackersClock, defendersClock, attackersTime, defendersTime);
    }

    /**
     * This function is called whenever a piece is moved to display the switch in turns and update the turn number.
     * @param c This parameter determines whose turn it is.
     * @param i This parameter is the current turn number.
     */
    public static void updateTurnInfo(char c, int i) {
        updateClock(c);
        if (c == attackers) {
            turn.setText("Turn: Attackers");
        }
        else if (c == defenders) {
            turn.setText("Turn: Defenders");
        }
        turnCount.setText("Turn Number: " + i);
    }

    /**
     * This function is called once the JPanel is fully formed and completed.
     * @return This returns the newly created JPanel when the program is ran.
     */
    public JPanel getBottomBar() {
        return bottom;
    }

    public static void updateClock(char c) {
        if(c == defenders) {
            attackersClock.addSeconds(perMoveTime);
            attackersTime.setText("Attackers Time: " + attackersClock.getTime());
        }else if ( c == attackers){
            defendersClock.addSeconds(perMoveTime);
            defendersTime.setText("Defenders Time: " + defendersClock.getTime());
        }
    }

    /**
	 * This function sets the game clock
	 */
    public static void setStartingTime(int s,int m,int h){
        startingHours = h;
        startingMinutes = m;
        startingSeconds = s;
    }

    /**
	 * This function sets the per move time
	 */
    public static void setPerMoveTime(int seconds){
        perMoveTime = seconds;
    }
}

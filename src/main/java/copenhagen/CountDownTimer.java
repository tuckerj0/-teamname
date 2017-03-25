package copenhagen;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

/**
 * This class is the thread implementation of the countdown timers for each side. This countdown timer acts like a chess
 * clock. If a particular player's clock hits 0, that player loses the game automatically.
 */
public class CountDownTimer {
    private Timer t;
    private char attackers = 'b';
    private char defenders = 'w';

    /**
     * This will create a new thread for a timer.
     */
    public CountDownTimer () {
       t = new java.util.Timer();
    }

    /**
     * This function is what actually runs the countdown for the timers. If one of the timers reaches zero, the timer
     * will stop and the opposing side wins the game.
     * @param attackersClock This parameter is the clock for the attacker side.
     * @param defendersClock This parameter is the clock for the defender side.
     * @param attackersTime This parameter is the JLabel for the attacker's side time left.
     * @param defendersTime This parameter is the JLabel for the defender's side time left.
     */
    public void runCountdown(Clock attackersClock, Clock defendersClock, JLabel attackersTime, JLabel defendersTime) {
        
        t.scheduleAtFixedRate(
                              new TimerTask()
                              {
            public void run()
            {
                if(Hnefatafl.getTurn() == attackers) {
                    attackersClock.decr();
                    attackersTime.setText("Attackers Time: " + attackersClock.getTime());
                    if (attackersClock.outOfTime()) {
                        new FinalMenu(defenders);
                        stopCountDown();
                    }
                } else {
                    defendersClock.decr();
                    defendersTime.setText("Defenders Time: " + defendersClock.getTime());
                    if (defendersClock.outOfTime()) {
                        new FinalMenu(attackers);
                        stopCountDown();
                    }
                }
            }
        },1000, 1000);
    }

    /**
     * This function stops the countdown timer.
     */
    public void stopCountDown() {
        t.cancel();
        t.purge();
    }
}

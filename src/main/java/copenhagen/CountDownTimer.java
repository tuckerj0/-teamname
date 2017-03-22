package copenhagen;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

public class CountDownTimer {
    private Timer t;
    private char attackers = 'b';
    private char defenders = 'w';
    
    public CountDownTimer () {
       t = new java.util.Timer();
    }
    
    
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
                        FinalMenu finalMenu = new FinalMenu(defenders);
                        stopCountDown();
                    }
                } else {
                    defendersClock.decr();
                    defendersTime.setText("Defenders Time: " + defendersClock.getTime());
                    if (defendersClock.outOfTime()) {
                        FinalMenu finalMenu = new FinalMenu(attackers);
                        stopCountDown();
                    }
                }
            }
        },1000, 1000);
    }
    
    public void stopCountDown() {
        t.cancel();
        t.purge();
    }

}

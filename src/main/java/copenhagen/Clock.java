package copenhagen;

import javax.swing.JLabel;

/**
 * This class is used as the clocks for the countdown timers for each side of the game. This countdown timer acts like a
 * chess clock. If a particular player's clock hits 0, that player loses the game automatically.
 */
public class Clock {
    private int hh;
    private int mm;
    private int ss;
    private JLabel clockLabel;

    /**
     * This create a new Clock object to have a countdown timer for each side (attackers and defenders).
     * @param hh This parameter is the amount of hours.
     * @param mm This parameter is the amount of minutes.
     * @param ss This parameter is the amount of seconds.
     */
    public Clock(int hh, int mm, int ss) {
        this.hh = hh;
        this.mm = mm;
        this.ss = ss;
    }

    /**
     * This function formats the clock into a JLabel to be displayed on the bottom bar.
     * @param teamName This parameter is the name of which side the clock belongs to (Attackers or Defenders).
     * @return This will return the clock represented as a JLabel.
     */
    public JLabel formatClock(String teamName) {
        clockLabel = new JLabel(getTime());
        clockLabel.setHorizontalAlignment(JLabel.CENTER);
        clockLabel.setVerticalAlignment(JLabel.CENTER);
        clockLabel.setText(teamName + " Time: " + getTime());
        return clockLabel;
    }

    /**
     * This function checks if the clock has reached all zeros.
     * @return This will return true if the clock has all zeros. Otherwise, it returns false.
     */
    public boolean outOfTime() {
        return (hh == 0 && mm == 0 && ss == 0);
    }
    
    /**
     * This function decreases the time by one second.
     */
    public void decr() {
        if (this.mm == 0 && this.ss == 0) {
            this.ss = 59;
            this.mm = 59;
            this.hh--;
        } else if (this.ss == 0) {
            this.ss = 59;
            this.mm--;
        } else this.ss--;
    }

    /**
     * This function returns the current time formatted as a string.
     * @return This returns the current time formatted as a string.
     */
    public String getTime() {
        String fHrs = String.format("%02d", this.hh);
        String fMins = String.format("%02d", this.mm);
        String fSecs = String.format("%02d", this.ss);
        String fTime = fHrs + ":" + fMins + ":" + fSecs;
        return fTime;
    }
    
    /**
     * This function adds a certain number of seconds to the countdown clock.
     * @param secs This is the number of seconds to add to the countdown clock.
     */
    public void addSeconds(int secs) {
        for(int i = 0; i < secs; i++){
            if (this.mm == 59 && this.ss == 59) {
                this.ss = 0;
                this.mm = 0;
                this.hh++;
            } else if (this.ss == 59) {
                this.ss = 0;
                this.mm++;
            } else this.ss++;
        }
    }
}

package copenhagen;
import javax.swing.JLabel;


public class Clock {
    private int hh;
    private int mm;
    private int ss;
    private JLabel clockLabel;

    
    public Clock(int hh, int mm, int ss) {
        this.hh = hh;
        this.mm = mm;
        this.ss = ss;
    }
    
    public JLabel formatClock(String teamName) {
        clockLabel = new JLabel();
        clockLabel.setHorizontalAlignment(JLabel.CENTER);
        clockLabel.setVerticalAlignment(JLabel.CENTER);
        clockLabel.setText(teamName + " Time: " + getTime());
        return clockLabel;
    
    }
    
    public boolean outOfTime() {
        return (hh == 0 && mm == 0 && ss == 0);
    }
    
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
     * This function returns the current time
     */
    public String getTime() {
        String fHrs = String.format("%02d", this.hh);
        String fMins = String.format("%02d", this.mm);
        String fSecs = String.format("%02d", this.ss);
        String fTime = fHrs + ":" + fMins + ":" + fSecs;
        return fTime;
    }
    
    /**
     * This function adds a certain number of seconds to the countdown clock
     * @param secs This is the number of seconds to add to the countdown clock
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

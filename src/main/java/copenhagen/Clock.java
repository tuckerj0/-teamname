package copenhagen;

public class Clock {
    private int hh;
    private int mm;
    private int ss;
    
    public Clock(int hh, int mm, int ss) {
        this.hh = hh;
        this.mm = mm;
        this.ss = ss;
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
    
    public String getTime() {
        String fHrs = String.format("%02d", this.hh);
        String fMins = String.format("%02d", this.mm);
        String fSecs = String.format("%02d", this.ss);
        String fTime = fHrs + ":" + fMins + ":" + fSecs;
        return fTime;
    }
    
    public void addSeconds(int secs) {
        for(int i = 0; i <= secs; i++){
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

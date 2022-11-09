package Warden.Members;

public class Member {
    private final long memberId;
    private int strikes = 0;
    private int warnings = 0;
    private int bans = 0;

    public Member(long memberId){
        this.memberId = memberId;
    }

    public void setStrikes(int strikes) {
        this.strikes = strikes;
    }

    public void setWarnings(int warnings) {
        this.warnings = warnings;
    }

    public void setBans(int bans) {
        this.bans = bans;
    }

    public long getMemberId() {
        return memberId;
    }

    public int getStrikes() {
        return strikes;
    }

    public int getWarnings() {
        return warnings;
    }

    public int getBans() {
        return bans;
    }
}

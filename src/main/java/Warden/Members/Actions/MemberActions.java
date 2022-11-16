package Warden.Members.Actions;

import Warden.Members.Member;

import java.sql.Timestamp;
import java.util.Random;

public class MemberActions extends Member {
    private final long actionId;
    private final Enum<Action> actionType;
    private final Timestamp date;
    private final long appliedBy;
    private final long guild_id;
    private final String reason;
    public MemberActions(long memberId, long actionId, Enum<Action> actionType, Timestamp date, long appliedBy, long guild_id, String reason) {
        super(memberId);

        this.actionId = actionId;
        this.actionType = actionType;
        this.date = date;
        this.appliedBy = appliedBy;
        this.guild_id = guild_id;
        this.reason = reason;
    }
    public MemberActions(long memberId, Enum<Action> actionType, Timestamp date, long appliedBy, long guild_id, String reason) {
        super(memberId);
        long randomLong = new Random().nextLong();
        this.actionId = randomLong < 0 ? randomLong * -1: randomLong;
        this.actionType = actionType;
        this.date = date;
        this.appliedBy = appliedBy;
        this.guild_id = guild_id;
        this.reason = reason;
    }

    public MemberActions(Member member,long memberId, Enum<Action> actionType, Timestamp date, long appliedBy, long guild_id, String reason) {
        super(member.getMemberId(),member.getStrikes(),member.getWarnings(),member.getBans());
        long randomLong = new Random().nextLong();
        this.actionId = randomLong < 0 ? randomLong * -1: randomLong;
        this.actionType = actionType;
        this.date = date;
        this.appliedBy = appliedBy;
        this.guild_id = guild_id;
        this.reason = reason;
    }

    public long getActionId() {
        return actionId;
    }

    public Enum<Action> getActionType() {
        return actionType;
    }

    public Timestamp getDate() {
        return date;
    }

    public long getAppliedBy() {
        return appliedBy;
    }

    public long getGuild_id() {
        return guild_id;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "**Action ID: " + actionId +
                "\nAction type: " + actionType +
                //"\tDate issued: " + date.toLocalDateTime().getMonth() + " " + date.toLocalDateTime().getDayOfMonth() + " " + date.toLocalDateTime().getYear() +
                "\nApplied by: <@" + appliedBy + ">"+
                "\nReason: " + reason + "**";
    }
}

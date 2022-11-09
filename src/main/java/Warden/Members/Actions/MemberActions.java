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
    public MemberActions(long memberId, long actionId, Enum<Action> actionType, Timestamp date, long appliedBy, long guild_id) {
        super(memberId);
        this.actionId = actionId;
        this.actionType = actionType;
        this.date = date;
        this.appliedBy = appliedBy;
        this.guild_id = guild_id;
    }
    public MemberActions(long memberId, Enum<Action> actionType, Timestamp date, long appliedBy, long guild_id) {
        super(memberId);
        long randomLong = new Random().nextLong();
        this.actionId = randomLong < 0 ? randomLong * -1: randomLong;
        this.actionType = actionType;
        this.date = date;
        this.appliedBy = appliedBy;
        this.guild_id = guild_id;
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
}

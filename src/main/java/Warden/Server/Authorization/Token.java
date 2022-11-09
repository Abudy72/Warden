package Warden.Server.Authorization;

import java.util.Random;
import java.util.UUID;

public class Token {
    private final String token;
    private long guild_id;
    private boolean claimed = false;
    private final long tokenId;
     public Token(String token, long guild_id, boolean claimed, long tokenId) {
        this.token = token;
        this.guild_id = guild_id;
        this.claimed = claimed;
        this.tokenId = tokenId;
    }

    //This will be used to create new tokens.
    public Token() {
        Random rd = new Random();
        this.tokenId = rd.nextLong();
        this.token = createToken();
    }
    private String createToken() {
        UUID newToken = UUID.randomUUID();
        return newToken.toString();
    }
    public String getToken() {
        return token;
    }

    public long getGuild_id() {
        return guild_id;
    }

    public boolean isClaimed() {
        return claimed;
    }
    protected void claimTicket() {
        this.claimed = true;
    }
    public long getTokenId() {
        return tokenId;
    }

    protected void setGuild_id(long guild_id){
         this.guild_id = guild_id;
    }
}

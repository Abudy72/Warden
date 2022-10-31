package Warden.Server;

import Warden.Server.Tokens.TokensDao;

import java.util.List;

public class Server implements ServerValidator {
    private final long guild_id;
    private final String guild_name;
    private final String owner;

    public Server(long guild_id, String guild_name, String owner) {
        this.guild_id = guild_id;
        this.guild_name = guild_name;
        this.owner = owner;
    }

    public long getGuild_id() {
        return guild_id;
    }

    public String getGuild_name() {
        return guild_name;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public boolean validateServer(WardenTokens wardenTokens) {
        TokensDao tokensDao = new TokensDao();
        List<WardenTokens> tokensList = tokensDao.getAll();
        boolean tokenStatus = tokensList.stream().anyMatch(token -> token.getToken().equals(wardenTokens.getToken()) && !token.isClaimed());
        if(tokenStatus){
            wardenTokens.claimTicket();
            tokensDao.update(wardenTokens);
            return true;
        }else return false;
    }
}

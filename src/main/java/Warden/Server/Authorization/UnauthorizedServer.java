package Warden.Server.Authorization;

import Warden.Server.ServerImpl;
import Warden.Server.Tokens.TokensDao;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class UnauthorizedServer extends ServerImpl implements ServerState {

    public UnauthorizedServer(long guild_id, String guild_name, String owner) {
        super(guild_id, guild_name, owner);
    }
    @Override
    public void registerServer(String suppliedToken) {
        TokensDao tokensDao = new TokensDao();
        List<WardenTokens> tokensList = tokensDao.getAll();
        tokensList.forEach(token -> {
            if(token.getToken().equals(suppliedToken)&& !token.isClaimed()){
                token.claimTicket();
                token.setGuild_id(this.getGuild_id());
                tokensDao.update(token);
            }
        });
    }

    @Override
    public boolean isServerVerified() {
        return false;
    }
}

package Warden.Server.Authorization;

import net.dv8tion.jda.api.entities.Guild;

import java.util.stream.Stream;

public class ServerRegistration {
    public static void registerServer(String token, Guild guild) throws NullPointerException{
        TokensDao tokensDao = new TokensDao();
        Stream<Token> unClaimedTickets = tokensDao.getAll().stream().filter(wardenTokens -> !wardenTokens.isClaimed() && wardenTokens.getToken().equals(token));
        Token wardenToken = unClaimedTickets.findFirst().isPresent() ? unClaimedTickets.findFirst().get() : null;
        wardenToken.setGuild_id(guild.getIdLong());
        wardenToken.claimTicket();
        tokensDao.update(wardenToken);
    }
}

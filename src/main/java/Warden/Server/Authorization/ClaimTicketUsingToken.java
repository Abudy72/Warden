package Warden.Server.Authorization;

import Warden.DataHandler.ErrorHandler;
import Warden.EventListeners.ResourceBundle;
import Warden.Server.ServerDao;
import Warden.Server.ServerImpl;
import net.dv8tion.jda.api.entities.Guild;

import static Warden.Main.DiscordStarterImpl.getInfoLogger;
import static Warden.Main.Driver.getMyLogger;

public class ClaimTicketUsingToken implements ResourceBundle,ServerRegistration {
    public ClaimTicketUsingToken(){}
    @Override
    public ErrorHandler registerServer(long token_id, Guild guild){
        getInfoLogger().info("Attempting to register server with id: " + guild + " TokenId used: " + token_id + "\nServer name: " + guild.getName() + ", Owner: ");
        TokensDao tokensDao = new TokensDao();
        ServerDao serverDao = new ServerDao();
        try{
            Token token = tokensDao.get(token_id).get();
            if(!token.isClaimed()){
                ServerImpl server = new ServerImpl(guild.getIdLong(),guild.getName(),"OWNER");
                serverDao.save(server);
                token.setGuild_id(guild.getIdLong());
                tokensDao.update(token);
                return null;
            }else{
                throw new IllegalStateException();
            }
        }catch (NullPointerException e){
            getMyLogger().warn(e.getMessage());
            return new ErrorHandler(TOKEN_ERR1,TOKEN_ERR1_MSG);
        }catch (IllegalArgumentException e){
            getMyLogger().warn(e.getMessage());
            return new ErrorHandler(TOKEN_ERR2,TOKEN_ERR2_MSG);
        }
    }
}

package Warden.Server.Authorization;

import Warden.DataHandler.ErrorHandler;
import Warden.EventListeners.ResourceBundle;
import Warden.ServerEntities.ServerEntities;
import Warden.ServerEntities.ServerEntitiesDao;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;

import java.awt.*;
import java.util.EnumSet;

import static Warden.Main.DiscordStarterImpl.getInfoLogger;
import static Warden.Main.Driver.getMyLogger;

public class RegisterServer implements ResourceBundle, ServerRegistration {
    @Override
    public ErrorHandler registerServer(long token_id, Guild guild) {
        TokensDao tokensDao = new TokensDao();
        try{
            Token token = tokensDao.get(token_id).get();
            if(!token.isClaimed()){
                token.setGuild_id(guild.getIdLong());
                token.claimTicket();
                configureServer(guild);
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
        } catch (Exception e){
            getMyLogger().fatal(e.getMessage());
            return new ErrorHandler(REGISTRATION_ERR,REGISTRATION_ERR_MSG);
        }
    }
    private void configureServer(Guild guild){
        getInfoLogger().info("Attempting to configure: " + guild.getIdLong());
        ServerEntitiesDao serverEntitiesDao = new ServerEntitiesDao();
        try{
            guild.createRole().setName("Warden Staff").setColor(Color.black).queue(role -> {
                ServerEntities roleEntity = new ServerEntities(guild.getIdLong(),NETWORK_MEMBER,role.getIdLong());
                guild.createCategory("Warden's_Corner").addPermissionOverride(guild.getPublicRole(),null, EnumSet.of(Permission.VIEW_CHANNEL)).
                        addPermissionOverride(role,EnumSet.of(Permission.VIEW_CHANNEL),null).queue(category -> {
                            ServerEntities categoryEntity = new ServerEntities(guild.getIdLong(),NETWORK_CATEGORY,category.getIdLong());
                            category.createTextChannel("network-announcements").addPermissionOverride(role,EnumSet.of(Permission.VIEW_CHANNEL,Permission.MESSAGE_SEND),null).queue(textChannel -> {
                                ServerEntities channelEntity = new ServerEntities(guild.getIdLong(),NETWORK_ANNOUNCEMENTS,textChannel.getIdLong());
                                //saving entities to database
                                serverEntitiesDao.save(categoryEntity);
                                serverEntitiesDao.save(channelEntity);
                                serverEntitiesDao.save(roleEntity);
                            });
                });
            });
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}

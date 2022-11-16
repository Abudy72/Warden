package Warden.Server;

import Warden.DataHandler.ErrorHandler;
import Warden.EventListeners.ResourceBundle;
import Warden.Server.Authorization.ServerRegistration;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.HashMap;

import static Warden.Main.DiscordStarterImpl.getInfoLogger;
import static Warden.Main.Driver.SERVER_ENTITIES;

public class ServerImpl implements Server, ServerRegistration, ResourceBundle {
    private final long guild_id;
    private final String guild_name;

    private ServerRegistration registration = null;
    public ServerImpl(long guild_id, String guild_name) {
        this.guild_id = guild_id;
        this.guild_name = guild_name;
    }
    public ServerImpl(long guild_id, String guild_name,ServerRegistration registration) {
        this.guild_id = guild_id;
        this.guild_name = guild_name;
        this.registration = registration;
    }
    @Override
    public long getGuild_id() {
        return this.guild_id;
    }
    @Override
    public String getName() {
        return this.guild_name;
    }

    @Override
    public void notifyServer(JDA jdaInstance, MessageEmbed messageEmbed) {
        HashMap<String,Long> serverEntities = SERVER_ENTITIES.get(this.guild_id);
        System.out.println(serverEntities.size());
        Long textChannelId = serverEntities.get(NETWORK_ANNOUNCEMENTS);

        jdaInstance.getGuildById(this.guild_id).getTextChannelById(textChannelId).sendMessageEmbeds(messageEmbed).queue();
        getInfoLogger().info(this.guild_id + " received a new message!");
    }

    public void setRegistration(ServerRegistration serverRegistration){
        this.registration = serverRegistration;
    }

    @Override
    public ErrorHandler registerServer(long token_id, Guild guild) {
        return registration.registerServer(token_id,guild);
    }
}

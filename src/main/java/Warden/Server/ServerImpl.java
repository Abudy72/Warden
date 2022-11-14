package Warden.Server;

import Warden.DataHandler.ErrorHandler;
import Warden.Server.Authorization.ServerRegistration;
import net.dv8tion.jda.api.entities.Guild;

public class ServerImpl implements Server, ServerRegistration {
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

    public void setRegistration(ServerRegistration serverRegistration){
        this.registration = serverRegistration;
    }

    @Override
    public ErrorHandler registerServer(long token_id, Guild guild) {
        return registration.registerServer(token_id,guild);
    }
}

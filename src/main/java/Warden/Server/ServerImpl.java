package Warden.Server;

import Warden.DataHandler.ErrorHandler;
import Warden.Server.Authorization.ServerRegistration;
import net.dv8tion.jda.api.entities.Guild;

public class ServerImpl implements Server, ServerRegistration {
    private final long guild_id;
    private final String guild_name;
    private final String owner;

    private ServerRegistration registration = null;
    public ServerImpl(long guild_id, String guild_name, String owner) {
        this.guild_id = guild_id;
        this.guild_name = guild_name;
        this.owner = owner;
    }
    public ServerImpl(long guild_id, String guild_name, String owner, ServerRegistration registration) {
        this.guild_id = guild_id;
        this.guild_name = guild_name;
        this.owner = owner;
        this.registration = registration;
    }
    @Override
    public long getGuild_id() {
        return this.guild_id;
    }
    @Override
    public String getOwner() {
        return this.owner;
    }
    @Override
    public String getName() {
        return this.guild_name;
    }

    @Override
    public ErrorHandler registerServer(long token_id, Guild guild) {
        return registration.registerServer(token_id,guild);
    }
}

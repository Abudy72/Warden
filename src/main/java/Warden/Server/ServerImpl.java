package Warden.Server;

import Warden.Server.Authorization.AuthorizedServer;
import Warden.Server.Authorization.ServerState;

public class ServerImpl implements Server {
    private final long guild_id;
    private final String guild_name;
    private final String owner;
    private ServerState serverState = new AuthorizedServer();
    public ServerImpl(long guild_id, String guild_name, String owner) {
        this.guild_id = guild_id;
        this.guild_name = guild_name;
        this.owner = owner;
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

    public void setServerState(ServerState serverState){
        this.serverState = serverState;
    }
    public void registerServer(String token){
        serverState.registerServer(token);
    }
    public boolean isServerVerified(){
        return this.serverState.isServerVerified();
    }
}

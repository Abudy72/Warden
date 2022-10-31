package Warden.Server;

public class ServerImpl implements Server {
    private final long guild_id;
    private final String guild_name;
    private final String owner;

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
        return this.getName();
    }
}

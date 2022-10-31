package Warden.Server;

public class Server {
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
}

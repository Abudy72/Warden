package Warden.ServerEntities;

import Warden.Server.ServerImpl;

import java.util.HashMap;

public class ServerEntities {
    private final long guild_id;
    private final String name;
    private final long entity_id;

    public ServerEntities(long guild_id, String name, long entity_id) {
        this.guild_id = guild_id;
        this.name = name;
        this.entity_id = entity_id;
    }

    public long getGuild_id() {
        return guild_id;
    }

    public String getName() {
        return name;
    }

    public long getEntity_id() {
        return entity_id;
    }
}

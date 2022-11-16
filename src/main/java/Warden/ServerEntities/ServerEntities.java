package Warden.ServerEntities;

import Warden.Server.ServerDao;

import java.util.HashMap;

import static Warden.Main.DiscordStarterImpl.getInfoLogger;

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

    public static HashMap<Long, HashMap<String,Long>> initializeEntities(){
        ServerEntitiesDao dao = new ServerEntitiesDao();
        ServerDao serverDao = new ServerDao();
        HashMap<Long,HashMap<String,Long>> result = new HashMap<>();

        serverDao.getAll().forEach(server -> {
            long serverId = server.getGuild_id();
            HashMap<String,Long> serverEntities = new HashMap<>();
            dao.getAll().stream().filter(entity -> entity.guild_id == serverId).forEach(serverEntity -> {
                serverEntities.put(serverEntity.getName(),serverEntity.getEntity_id());
                result.put(serverId,serverEntities);
            });
        });
        getInfoLogger().info(result.size() + " Server Entities loaded.");
        return result;
    }
}

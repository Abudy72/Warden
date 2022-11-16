package Warden.Main;

import Warden.Flyway.FlywayMigration;
import Warden.Server.NotificationSystem.Publisher;
import Warden.Server.ServerDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

import static Warden.ServerEntities.ServerEntities.initializeEntities;

public class Driver {
    private final static Logger logger = LogManager.getLogger();
    public static HashMap<Long,  HashMap<String,Long>> SERVER_ENTITIES = initializeEntities();
    public static void main(String[] args){
        FlywayMigration.migrate();
        DiscordStarter discordStarter = new DiscordStarterImpl();
        discordStarter.start();
    }
    public static Logger getMyLogger(){
        return logger;
    }
}

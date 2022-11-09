package Warden.Main;

import Warden.Flyway.FlywayMigration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Driver {
    private final static Logger logger = LogManager.getLogger();
    public static void main(String[] args){
        FlywayMigration.migrate();
        DiscordStarter discordStarter = new DiscordStarterImpl();
        discordStarter.start();
    }

    public static Logger getMyLogger(){
        return logger;
    }
}

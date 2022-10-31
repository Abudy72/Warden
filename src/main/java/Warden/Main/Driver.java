package Warden.Main;

import Warden.Flyway.FlywayMigration;

import java.net.URISyntaxException;

public class Driver {
    public static void main(String[] args) throws URISyntaxException {
        FlywayMigration.migrate();
    }
}

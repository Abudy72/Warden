package Warden.Main;

import Warden.Flyway.FlywayMigration;

public class Driver {

    public static void main(String[] args) {
        FlywayMigration.migrate();
    }
}

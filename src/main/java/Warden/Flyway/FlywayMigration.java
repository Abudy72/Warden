package Warden.Flyway;


import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.output.MigrateResult;

import java.net.URI;
import java.net.URISyntaxException;

public class FlywayMigration {
    public static void migrate() {
        URI dbUri = null;
        try {
            dbUri = new URI(System.getenv("DATABASE_URL"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        Flyway flyway = Flyway.configure().dataSource(dbUrl,username,password).defaultSchema("Warden").load();
        MigrateResult migrate= flyway.migrate();
        System.out.println("Success status: " + migrate.success);
        System.out.println("Migrations: " + migrate.migrationsExecuted);
        System.out.println("Migration version: " + migrate.initialSchemaVersion);
    }

    private FlywayMigration(){}
}

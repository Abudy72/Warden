package Warden.Flyway;


import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.output.MigrateResult;

public abstract class FlywayMigration {
    private final static String dbURL = "jdbc:postgresql://localhost:5432/postgres";
    public static void migrate(){
        Flyway flyway = Flyway.configure().dataSource(dbURL,"postgres","postgres").load();
        MigrateResult migrate= flyway.migrate();
        System.out.println("Success status: " + migrate.success);
        System.out.println("Migrations: " + migrate.migrationsExecuted);
        System.out.println("Migration version: " + migrate.initialSchemaVersion);

    }
}

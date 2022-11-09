package Warden.ConnectionPooling;

import org.apache.commons.dbcp.BasicDataSource;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import static Warden.Main.Driver.getMyLogger;

public class ConnectionManager {
    private static final BasicDataSource dataSource = new BasicDataSource();
    static {
        String username = null, password = null,dbUrl = null;
        URI dbUri;
        try{
            dbUri = new URI(System.getenv("DATABASE_URL"));
            username = dbUri.getUserInfo().split(":")[0];
            password = dbUri.getUserInfo().split(":")[1];
            dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        }catch (Exception e){
            getMyLogger().fatal(e.getMessage());
        }

        //Setting database credentials
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxActive(20);
        List<String> p = new LinkedList<>();
        p.add("SET SCHEMA 'Warden';");
        dataSource.setConnectionInitSqls(p);
    }

    public static Connection getConnection(){
        try{
            Connection connection = dataSource.getConnection();
            return connection;
        }catch (SQLException e){
            e.printStackTrace();
         throw new RuntimeException(e.getMessage());
        }
    }
    private ConnectionManager(){}
}

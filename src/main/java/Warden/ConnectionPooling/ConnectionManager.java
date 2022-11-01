package Warden.ConnectionPooling;

import org.apache.commons.dbcp.BasicDataSource;

import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectionManager {
    private static BasicDataSource dataSource = new BasicDataSource();
    static {
        String username = null, password = null,dbUrl = null;
        URI dbUri;
        try{
            dbUri = new URI(System.getenv("DATABASE_URL"));
            username = dbUri.getUserInfo().split(":")[0];
            password = dbUri.getUserInfo().split(":")[1];
            dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        }catch (Exception e){
            Logger logger = Logger.getLogger(ConnectionManager.class.getName());
            logger.severe(e.getMessage());
        }

        System.out.println(password);
        //Setting database credentials
        dataSource.setUrl(dbUrl);
        dataSource.setUrl(username);
        dataSource.setPassword(password);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxActive(20);
    }

    public static Connection getConnection(){
        try{
            Connection connection = dataSource.getConnection();
            connection.setSchema("Warden");
            return connection;
        }catch (SQLException e){
            Logger logger = Logger.getLogger(ConnectionManager.class.getName());
            logger.severe(e.getMessage());
        }
        return null;
    }
    private ConnectionManager(){}
}

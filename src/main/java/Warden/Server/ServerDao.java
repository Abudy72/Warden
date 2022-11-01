package Warden.Server;

import Warden.ConnectionPooling.ConnectionManager;
import Warden.Dao.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static Warden.Main.Driver.getMyLogger;

public class ServerDao implements Dao<ServerImpl> {
    @Override
    public Optional<ServerImpl> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<ServerImpl> getAll() {
        return null;
    }

    @Override
    public boolean save(ServerImpl serverImpl) {
        String statement = "INSERT INTO values (?,?,?)";
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setLong(1,serverImpl.getGuild_id());
            preparedStatement.setString(2,serverImpl.getName());
            preparedStatement.setString(3,serverImpl.getOwner());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            getMyLogger().fatal(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(ServerImpl serverImpl) {
        return false;
    }

    @Override
    public boolean delete(ServerImpl serverImpl) {
        return false;
    }
}

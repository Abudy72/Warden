package Warden.Server;

import Warden.ConnectionPooling.ConnectionManager;
import Warden.Dao.Dao;
import Warden.ServerEntities.ServerEntities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static Warden.Main.Driver.getMyLogger;

public class ServerDao implements Dao<ServerImpl> {
    @Override
    public Optional<ServerImpl> get(long id) {
        String statement = "SELECT * FROM servers where guild_id = ?";
        try{
            Connection connection = ConnectionManager.getConnection();
            ResultSet resultSet = connection.prepareStatement(statement).executeQuery();
            if(resultSet.next()){
                ServerImpl server = new ServerImpl(
                        resultSet.getLong("guild_id"),
                        resultSet.getString("name"),
                        resultSet.getString("owner")
                );
                return Optional.of(server);
            }
        }catch (SQLException e){
            getMyLogger().error("Unable to prepare statement, Check syntax please. ");

        }
        return Optional.empty();
    }

    @Override
    public List<ServerImpl> getAll() {
        String statement = "SELECT * FROM servers";
        LinkedList<ServerImpl> resultList = new LinkedList<>();
        try{
            Connection connection = ConnectionManager.getConnection();
            ResultSet resultSet = connection.prepareStatement(statement).executeQuery();
            if(resultSet.next()){
                ServerImpl server = new ServerImpl(
                        resultSet.getLong("guild_id"),
                        resultSet.getString("name"),
                        resultSet.getString("owner")
                );
                resultList.add(server);
            }
        }catch (SQLException e){
            getMyLogger().error("Unable to prepare statement, Check syntax please. ");

        }
        return resultList;
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
        String statement = "UPDATE servers set (name,owner) = (?,?) where guild_id = ?";
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1,serverImpl.getName());
            preparedStatement.setString(2,serverImpl.getOwner());
            preparedStatement.setLong(3,serverImpl.getGuild_id());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            getMyLogger().fatal(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(ServerImpl serverImpl) {
        String statement = "DROP FROM servers where guild_id = ?";
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setLong(1,serverImpl.getGuild_id());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            getMyLogger().fatal(e.getMessage());
            return false;
        }
    }


}

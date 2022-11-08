package Warden.ServerEntities;

import Warden.ConnectionPooling.ConnectionManager;
import Warden.Dao.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static Warden.Main.Driver.getMyLogger;

public class EntitiesDao implements Dao<ServerEntities> {
    @Override
    public Optional<ServerEntities> get(long id) {
        String statement = "SELECT * FROM server_entities where entity_id = ?";
        try{
            Connection connection = ConnectionManager.getConnection();
            ResultSet resultSet = connection.prepareStatement(statement).executeQuery();
            if(resultSet.next()){
                ServerEntities serverEntities = new ServerEntities(
                        resultSet.getLong("guild_id"), resultSet.getString("name"),resultSet.getLong("entity_id"));
                return Optional.of(serverEntities);
            }
        }catch (SQLException e){
            getMyLogger().error("Unable to prepare statement, Check syntax please. ");

        }
        return Optional.empty();
    }

    @Override
    public List<ServerEntities> getAll() {
        String statement = "SELECT * FROM server_entities";
        LinkedList<ServerEntities> resultList = new LinkedList<>();
        try{
            Connection connection = ConnectionManager.getConnection();
            ResultSet resultSet = connection.prepareStatement(statement).executeQuery();
            if(resultSet.next()){
                ServerEntities serverEntities = new ServerEntities(
                        resultSet.getLong("guild_id"), resultSet.getString("name"),resultSet.getLong("entity_id"));
                resultList.add(serverEntities);
            }
        }catch (SQLException e){
            getMyLogger().error("Unable to prepare statement, Check syntax please. ");

        }
        return resultList;
    }

    @Override
    public boolean save(ServerEntities serverEntities) {
        String statement = "INSERT INTO server_entities values (?,?,?)";
        LinkedList<ServerEntities> resultList = new LinkedList<>();
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setLong(1,serverEntities.getGuild_id());
            preparedStatement.setString(2,serverEntities.getName());
            preparedStatement.setLong(3,serverEntities.getEntity_id());

            return preparedStatement.executeUpdate() == 1;
        }catch (SQLException e){
            getMyLogger().error("Unable to prepare statement, Check syntax please. ");

        }
        return false;
    }

    @Override
    public boolean update(ServerEntities serverEntities) {
        String statement = "UPDATE server_entities set (guild_id,entity_name,entity_id) = (?,?,?) where guild_id = ? AND entity_name = ?";
        LinkedList<ServerEntities> resultList = new LinkedList<>();
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setLong(1,serverEntities.getGuild_id());
            preparedStatement.setString(2,serverEntities.getName());
            preparedStatement.setLong(3,serverEntities.getEntity_id());

            preparedStatement.setLong(4,serverEntities.getGuild_id());
            preparedStatement.setString(5,serverEntities.getName());

            return preparedStatement.executeUpdate() == 1;
        }catch (SQLException e){
            getMyLogger().error("Unable to prepare statement, Check syntax please. ");

        }
        return false;
    }

    @Override
    public boolean delete(ServerEntities serverEntities) {
        String statement = "DELETE from server_entities where guild_id = ? and entity_id = ?";
        LinkedList<ServerEntities> resultList = new LinkedList<>();
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setLong(1,serverEntities.getGuild_id());
            preparedStatement.setLong(2,serverEntities.getEntity_id());

            return preparedStatement.executeUpdate() == 1;
        }catch (SQLException e){
            getMyLogger().error("Unable to prepare statement, Check syntax please. ");

        }
        return false;
    }
}

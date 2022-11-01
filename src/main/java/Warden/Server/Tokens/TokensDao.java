package Warden.Server.Tokens;

import Warden.ConnectionPooling.ConnectionManager;
import Warden.Dao.Dao;
import Warden.Server.Authorization.WardenTokens;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static Warden.Main.Driver.getMyLogger;

public class TokensDao implements Dao<WardenTokens> {
    @Override
    public Optional<WardenTokens> get(long id) {
        String statement = "SELECT * from warden_tokens where id = ?";
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                WardenTokens wardenTokens = new WardenTokens(
                        resultSet.getString("token"),
                        resultSet.getLong("guild_id"),
                        resultSet.getBoolean("is_claimed"),
                        resultSet.getLong("token_id")
                );
                return Optional.of(wardenTokens);
            }
        }catch (SQLException e){
            getMyLogger().fatal(e.getMessage());
        }
        return Optional.empty();
    }
    @Override
    public List<WardenTokens> getAll() {
        String statement = "SELECT * from warden_tokens";
        List<WardenTokens> wardenTokensList = new LinkedList<>();
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                WardenTokens wardenTokens = new WardenTokens(
                        resultSet.getString("token"),
                        resultSet.getLong("guild_id"),
                        resultSet.getBoolean("is_claimed"),
                        resultSet.getLong("token_id")
                );
                wardenTokensList.add(wardenTokens);
            }
        }catch (SQLException e){
            getMyLogger().fatal(e.getMessage());
            e.printStackTrace();
        }
        return wardenTokensList;
    }
    @Override
    public boolean save(WardenTokens wardenTokens) {
        String statement = "INSERT INTO warden_tokens (claimed_by,token,is_claimed) values (?,?,?)";
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setLong(1,wardenTokens.getGuild_id());
            preparedStatement.setString(2,wardenTokens.getToken());
            preparedStatement.setBoolean(3,wardenTokens.isClaimed());
            return preparedStatement.executeUpdate() == 1;
        }catch (SQLException e){
            getMyLogger().fatal(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean update(WardenTokens wardenTokens) {
        String statement = "UPDATE warden_tokens set (claimed_by,token,is_claimed) = (?,?,?) where token = ?";
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setLong(1,wardenTokens.getGuild_id());
            preparedStatement.setString(2,wardenTokens.getToken());
            preparedStatement.setBoolean(3,wardenTokens.isClaimed());
            preparedStatement.setString(4,wardenTokens.getToken());
            return preparedStatement.executeUpdate() == 1;
        }catch (SQLException e){
            getMyLogger().fatal(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean delete(WardenTokens wardenTokens) {
        String statement = "DELETE FROM warden_tokens where id=?";
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setLong(1,wardenTokens.getTokenId());
            return preparedStatement.executeUpdate() == 1;
        }catch (SQLException e){
            getMyLogger().fatal(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}

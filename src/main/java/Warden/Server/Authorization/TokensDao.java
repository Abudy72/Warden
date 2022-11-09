package Warden.Server.Authorization;

import Warden.ConnectionPooling.ConnectionManager;
import Warden.Dao.Dao;
import Warden.Server.Authorization.Token;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static Warden.Main.Driver.getMyLogger;

public class TokensDao implements Dao<Token> {
    @Override
    public Optional<Token> get(long id) {
        String statement = "SELECT * from warden_tokens where id = ?";
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Token token = new Token(
                        resultSet.getString("token"),
                        resultSet.getLong("claimed_by"),
                        resultSet.getBoolean("is_claimed"),
                        resultSet.getLong("id")
                );
                return Optional.of(token);
            }
        }catch (SQLException e){
            getMyLogger().fatal(e.getMessage());
        }
        return Optional.empty();
    }
    @Override
    public List<Token> getAll() {
        String statement = "SELECT * from warden_tokens";
        List<Token> tokenList = new LinkedList<>();
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Token token = new Token(
                        resultSet.getString("token"),
                        resultSet.getLong("claimed_by"),
                        resultSet.getBoolean("is_claimed"),
                        resultSet.getLong("id")
                );
                tokenList.add(token);
            }
        }catch (SQLException e){
            getMyLogger().fatal(e.getMessage());
            e.printStackTrace();
        }
        return tokenList;
    }
    @Override
    public boolean save(Token token) {
        String statement = "INSERT INTO warden_tokens (token) values (?)";
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, token.getToken());
            return preparedStatement.executeUpdate() == 1;
        }catch (SQLException e){
            getMyLogger().fatal(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean update(Token token) {
        String statement = "UPDATE warden_tokens set (claimed_by,token,is_claimed) = (?,?,?) where token = ?";
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setLong(1, token.getGuild_id());
            preparedStatement.setString(2, token.getToken());
            preparedStatement.setBoolean(3, token.isClaimed());
            preparedStatement.setString(4, token.getToken());
            return preparedStatement.executeUpdate() == 1;
        }catch (SQLException e){
            getMyLogger().fatal(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean delete(Token token) {
        String statement = "DELETE FROM warden_tokens where id=?";
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setLong(1, token.getTokenId());
            return preparedStatement.executeUpdate() == 1;
        }catch (SQLException e){
            getMyLogger().fatal(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}

package Warden.Members.Actions;

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

public class ActionsDao implements Dao<MemberActions> {
    @Override
    public Optional<MemberActions> get(long id) {
        String statement = "SELECT * FROM actions where action_id = ?";
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = connection.prepareStatement(statement).executeQuery();
            if(resultSet.next()){
                MemberActions action = new MemberActions(
                        resultSet.getLong("member_id"),
                        resultSet.getLong("action_id"),
                        Action.valueOf(resultSet.getString("action_type")),
                        resultSet.getTimestamp("date_applied"),
                        resultSet.getLong("applied_by"),
                        resultSet.getLong("guild_id"),
                        resultSet.getString("reason")
                );
                return Optional.of(action);
            }
        }catch (SQLException e){
            getMyLogger().error("Unable to prepare statement, Check syntax please.");

        }
        return Optional.empty();
    }

    @Override
    public List<MemberActions> getAll() {
        String statement = "SELECT * FROM actions";
        List<MemberActions> resultList = new LinkedList<>();
        try{
            Connection connection = ConnectionManager.getConnection();
            ResultSet resultSet = connection.prepareStatement(statement).executeQuery();
            if(resultSet.next()){
                MemberActions action = new MemberActions(
                        resultSet.getLong("member_id"),
                        resultSet.getLong("action_id"),
                        Action.valueOf(resultSet.getString("action_type")),
                        resultSet.getTimestamp("date_applied"),
                        resultSet.getLong("applied_by"),
                        resultSet.getLong("guild_id"),
                        resultSet.getString("reason")
                );
               resultList.add(action);
            }
        }catch (SQLException e){
            getMyLogger().error("Unable to prepare statement, Check syntax please.");

        }
        return resultList;
    }

    @Override
    public boolean save(MemberActions memberActions) {
        String statement = "INSERT INTO actions values (?,?,?,?,?,?,?)";
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setLong(1,memberActions.getActionId());
            preparedStatement.setLong(2,memberActions.getMemberId());
            preparedStatement.setLong(3,memberActions.getGuild_id());
            preparedStatement.setString(4,memberActions.getReason());
            preparedStatement.setString(5,memberActions.getActionType().toString());
            preparedStatement.setLong(6,memberActions.getAppliedBy());
            preparedStatement.setTimestamp(7,memberActions.getDate());
            return preparedStatement.executeUpdate() == 1;
        }catch (SQLException e){
            getMyLogger().error("Unable to prepare statement, Check syntax please.");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(MemberActions memberActions) {
        String statement = "UPDATE actions set (action_type,reason) = (?,?) where action_id = ?";
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1,memberActions.getActionType().toString());
            preparedStatement.setString(2,memberActions.getReason());
            preparedStatement.setLong(3,memberActions.getActionId());
            return preparedStatement.executeUpdate() == 1;
        }catch (SQLException e){
            getMyLogger().error("Unable to prepare statement, Check syntax please.");

        }
        return false;
    }

    @Override
    public boolean delete(MemberActions memberActions) {
        String statement = "DELETE FROM actions where action_id = ?";
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setLong(1,memberActions.getActionId());
            return preparedStatement.executeUpdate() == 1;
        }catch (SQLException e){
            getMyLogger().error("Unable to prepare statement, Check syntax please.");

        }
        return false;
    }
}

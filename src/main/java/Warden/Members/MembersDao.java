package Warden.Members;

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

public class MembersDao implements Dao<Member> {
    @Override
    public Optional<Member> get(long id) {
        String statement = "SELECT * FROM members where member_id = ?";
        try{
            Connection connection = ConnectionManager.getConnection();
            ResultSet resultSet = connection.prepareStatement(statement).executeQuery();
            if(resultSet.next()){
                Member member = new Member(
                        resultSet.getLong("member_id")
                );
                member.setBans(resultSet.getInt("bans"));
                member.setStrikes(resultSet.getInt("strikes"));
                member.setWarnings(resultSet.getInt("warnings"));
                return Optional.of(member);
            }
        }catch (SQLException e){
            getMyLogger().error("Unable to prepare statement, Check syntax please.");

        }
        return Optional.empty();
    }

    @Override
    public List<Member> getAll() {
        String statement = "SELECT * FROM members";
        LinkedList<Member> resultList = new LinkedList<>();
        try{
            Connection connection = ConnectionManager.getConnection();
            ResultSet resultSet = connection.prepareStatement(statement).executeQuery();
            if(resultSet.next()){
                Member member = new Member(
                        resultSet.getLong("member_id")
                );
                member.setBans(resultSet.getInt("bans"));
                member.setStrikes(resultSet.getInt("strikes"));
                member.setWarnings(resultSet.getInt("warnings"));
                resultList.add(member);
            }
        }catch (SQLException e){
            getMyLogger().error("Unable to prepare statement, Check syntax please. ");

        }
        return resultList;
    }

    @Override
    public boolean save(Member member) {
        String statement = "INSERT INTO members values (?,?,?,?)";
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setLong(1,member.getMemberId());
            preparedStatement.setInt(2,member.getStrikes());
            preparedStatement.setInt(3,member.getWarnings());
            preparedStatement.setInt(4,member.getBans());
            return preparedStatement.executeUpdate() == 1;
        }catch (SQLException e){
            getMyLogger().error("Unable to prepare statement, Check syntax please. ");

        }
        return false;
    }

    @Override
    public boolean update(Member member) {
        String statement = "UPDATE members set (bans,strikes,warnings) = (?,?,?) where member_id = ?";
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1,member.getBans());
            preparedStatement.setInt(2,member.getStrikes());
            preparedStatement.setInt(3,member.getWarnings());
            preparedStatement.setLong(4,member.getMemberId());
            return preparedStatement.executeUpdate() == 1;
        }catch (SQLException e){
            getMyLogger().error("Unable to prepare statement, Check syntax please. ");

        }
        return false;
    }

    @Override
    public boolean delete(Member member) {
        String statement = "DELETE FROM members where member_id = ?";
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setLong(1,member.getMemberId());
            return preparedStatement.executeUpdate() == 1;
        }catch (SQLException e){
            getMyLogger().error("Unable to prepare statement, Check syntax please. ");

        }
        return false;
    }
}

package Warden.Members;

import Warden.ConnectionPooling.ConnectionManager;
import Warden.Dao.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static Warden.Main.Driver.getMyLogger;

public class MembersDao implements Dao<Member> {
    @Override
    public Optional<Member> get(long id) {
        String statement = "SELECT * FROM members where id = ?";
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
        return null;
    }

    @Override
    public boolean save(Member member) {
        return false;
    }

    @Override
    public boolean update(Member member) {
        return false;
    }

    @Override
    public boolean delete(Member member) {
        return false;
    }
}

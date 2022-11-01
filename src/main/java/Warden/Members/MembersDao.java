package Warden.Members;

import Warden.Dao.Dao;

import java.util.List;
import java.util.Optional;

public class MembersDao implements Dao<Member> {
    @Override
    public Optional<Member> get(long id) {
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

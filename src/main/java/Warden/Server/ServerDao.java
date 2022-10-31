package Warden.Server;

import Warden.Dao.Dao;

import java.util.List;
import java.util.Optional;

public class ServerDao implements Dao<Server> {

    @Override
    public Optional<Server> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Server> getAll() {
        return null;
    }

    @Override
    public boolean save(Server server) {
        return false;
    }

    @Override
    public boolean update(Server server) {
        return false;
    }

    @Override
    public boolean delete(Server server) {
        return false;
    }
}

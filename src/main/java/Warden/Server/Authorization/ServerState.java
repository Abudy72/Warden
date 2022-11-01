package Warden.Server.Authorization;

public interface ServerState {
    void registerServer(String token);
    boolean isServerVerified();
}

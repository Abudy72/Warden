package Warden.Server.Authorization;

import Warden.Server.ServerImpl;

public class AuthorizedServer implements ServerState {
    @Override
    public void registerServer(String token) {
        throw new IllegalStateException("Server is already verified, unable to verify again. If you think this is a mistake please raise a ticket in Discord Restoration");
    }

    @Override
    public boolean isServerVerified() {
        return true;
    }
}

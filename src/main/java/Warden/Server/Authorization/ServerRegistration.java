package Warden.Server.Authorization;

import Warden.DataHandler.ErrorHandler;
import net.dv8tion.jda.api.entities.Guild;

public interface ServerRegistration {
    ErrorHandler registerServer(long token_id, Guild guild);
}

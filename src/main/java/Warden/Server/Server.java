package Warden.Server;

import Warden.Members.Actions.MemberActions;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;

public interface Server {
    long getGuild_id();
    String getName();
    void notifyServer(JDA jdaInstance, MessageEmbed msg);
}

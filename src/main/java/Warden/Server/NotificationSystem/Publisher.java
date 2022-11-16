package Warden.Server.NotificationSystem;

import Warden.Members.Actions.MemberActions;
import Warden.Server.Server;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.LinkedList;

public class Publisher {
    private final LinkedList<Server> network_Servers = new LinkedList<>();

    public void subscribeToNetwork(Server server){
        network_Servers.add(server);
    }
    public void publishNewAction(JDA jdaInstance, MessageEmbed msg){
        for(Server s: network_Servers){
            s.notifyServer(jdaInstance,msg);
        }
    }

    public int getSubscriberCount(){
        return network_Servers.size();
    }
}

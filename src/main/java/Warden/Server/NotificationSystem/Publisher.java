package Warden.Server.NotificationSystem;

import Warden.Server.Server;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.ArrayList;

public class Publisher {
    private final ArrayList<Server> network_Servers = new ArrayList<>();

    public void subscribeToNetwork(Server server){
        network_Servers.add(server);
    }
    public void publishNewAction(Server server,JDA jdaInstance, MessageEmbed msg){
        removeSubscriber(server);
        for(Server s: network_Servers){
            s.notifyServer(jdaInstance,msg);
        }
        subscribeToNetwork(server);
    }

    public int getSubscriberCount(){
        return network_Servers.size();
    }

    public void removeSubscriber(Server server){
        network_Servers.remove(server);
    }
}

package Warden.EventListeners.SlashCommand.ButtonEventListeners;

import Warden.DataHandler.ErrorHandler;
import Warden.Server.Authorization.RegisterServer;
import Warden.Server.ServerDao;
import Warden.Server.ServerImpl;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class ServerRegistrationEventListener extends ListenerAdapter {
    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        disableButton(event);
        if(!event.getButton().getId().equals("false")){
            ServerDao dao = new ServerDao();
            Guild guild = event.getGuild();
            System.out.println(guild.getIdLong());
            ServerImpl server = dao.get(guild.getIdLong()).get();
            server.setRegistration(new RegisterServer());
            long token = Long.parseLong(event.getButton().getId());
            ErrorHandler errorHandler = server.registerServer(token,guild);
            EmbedBuilder builder = new EmbedBuilder();
            if(errorHandler == null){
                builder.setTitle("Thank you, Everything mentioned was created! Please head over to Warden's Corner and" +
                        " verify permissions.");
                builder.setColor(Color.GREEN);
                builder.setFooter(event.getGuild().getName(),event.getGuild().getIconUrl());
                builder.setAuthor("Warden!");
                sendPrivateMessage(event, builder);
            }else {
                builder.setTitle("I was not able to process that, please contact Discord Restoration");
                builder.setDescription(errorHandler.getERROR_CODE() + " " + errorHandler.getERROR_MSG());
                sendPrivateMessage(event, builder);
            }
        }else{
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("If you ever change your mind, run the same command, with the same token again!");
            sendPrivateMessage(event, builder);
        }
    }

    private void sendPrivateMessage(@NotNull ButtonInteractionEvent event, EmbedBuilder builder) {
        builder.setColor(Color.RED);
        builder.setFooter(event.getGuild().getName(),event.getGuild().getIconUrl());
        builder.setAuthor("Warden!");
        User user = event.getInteraction().getUser();
        user.openPrivateChannel().queue(privateChannel -> {
            privateChannel.sendMessageEmbeds(builder.build()).queue();
        });
    }

    private void disableButton(ButtonInteractionEvent event){
        event.getMessage().getActionRows().forEach(row -> {
            event.editComponents(row.asDisabled()).queue();
        });
    }
}

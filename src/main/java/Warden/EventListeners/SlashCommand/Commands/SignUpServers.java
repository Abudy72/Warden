package Warden.EventListeners.SlashCommand.Commands;

import Warden.DataHandler.ErrorHandler;
import Warden.EventListeners.ResourceBundle;
import Warden.EventListeners.SlashCommand.CommandStrategy;
import Warden.Server.Authorization.ClaimTicketUsingToken;
import Warden.Server.ServerImpl;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import static Warden.Main.Driver.getMyLogger;

public class SignUpServers extends CommandStrategy implements ResourceBundle {
    static final String ACCEPT_STRING = "✅ Accept";
    static final String DECLINE_STRING = "❌ Decline";
    @Override
    public void executeCommand(SlashCommandInteractionEvent event) {
        try{
            String givenToken = event.getOption(TOKEN_ID, OptionMapping::getAsString);
            long token = Long.parseLong(givenToken);

            Guild guild = event.getGuild();
            ServerImpl server = new ServerImpl(event.getIdLong(),event.getName(), new ClaimTicketUsingToken());
            ErrorHandler errorHandler = server.registerServer(token,guild);
            if(errorHandler == null){
                EmbedBuilder msgBuilder= prepareEmbedMessage();
                event.replyEmbeds(createResponse(event,"Owner's approval is required to proceed.",msgBuilder)).setEphemeral(true).addActionRow(
                        Button.success(givenToken,ACCEPT_STRING),
                        Button.danger("false",DECLINE_STRING)
                ).queue();
            }else {
                EmbedBuilder embedBuilder = new EmbedBuilder();
                event.replyEmbeds(createResponse(event, errorHandler.getERROR_CODE() + ": " + errorHandler.getERROR_MSG(),embedBuilder)).setEphemeral(true).queue();
            }
        }catch (NullPointerException e){
            getMyLogger().warn(e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public EmbedBuilder prepareEmbedMessage() {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setDescription("By proceeding, you will give me permission to do the following.");
        embedBuilder.addField(new Field("1. Creating a new category, it will be called `Warden's Corner`.","All warden related channels will be listed here.",false));
        embedBuilder.addField(new Field("2. Creating a new channel, it will be called `network-announcements`.","All notifications about member actions will be posted here.",false));
        embedBuilder.addField(new Field("3. Creating a new role, it will be called `Warden Staff`.","Server members will be able to issue an action and follow up on notifications",false));
        return embedBuilder;
    }
}










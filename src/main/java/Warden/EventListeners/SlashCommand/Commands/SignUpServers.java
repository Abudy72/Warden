package Warden.EventListeners.SlashCommand.Commands;

import Warden.DataHandler.ErrorHandler;
import Warden.EventListeners.ResourceBundle;
import Warden.EventListeners.SlashCommand.CommandStrategy;

import Warden.Server.Authorization.ClaimTicketUsingToken;
import Warden.Server.Authorization.RegisterServer;
import Warden.Server.ServerImpl;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import static Warden.Main.Driver.getMyLogger;

public class SignUpServers extends CommandStrategy implements ResourceBundle {
    @Override
    public void executeCommand(SlashCommandInteractionEvent event) {
        try{
            String givenToken = event.getOption(TOKEN_ID, OptionMapping::getAsString);
            long token = Long.parseLong(givenToken);
            //Make this call ONLY IF roles, channels and categories were made.
            Guild guild = event.getGuild();
            ServerImpl server = new ServerImpl(event.getGuild().getIdLong(),event.getGuild().getName(),
                   "NAME", new RegisterServer());
            ErrorHandler errorHandler = server.registerServer(token,event.getGuild());
            if(errorHandler == null){
                event.reply("Your server is now ready!").queue();
            }else {
                event.reply(errorHandler.getERROR_CODE() + ": " + errorHandler.getERROR_MSG()).queue();
            }
        }catch (NullPointerException e){
            getMyLogger().warn(e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public EmbedBuilder prepareEmbedMessage() {
        return null;
    }
}

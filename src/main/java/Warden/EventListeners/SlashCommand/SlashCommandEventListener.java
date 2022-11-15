package Warden.EventListeners.SlashCommand;

import Warden.EventListeners.ResourceBundle;
import Warden.EventListeners.SlashCommand.Commands.CreateToken;
import Warden.EventListeners.SlashCommand.Commands.IssueAction;
import Warden.EventListeners.SlashCommand.Commands.SignUpServers;
import Warden.Members.Actions.Action;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class SlashCommandEventListener extends ListenerAdapter implements ResourceBundle {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        switch (event.getName()){
            case CREATE_TOKEN:
                if(event.getMember().getId().equals("199707685459984384")){ //ONLY FOR ROYAL
                    new CreateToken().executeCommand(event);
                }else defaultMessage(event);
                break;
            case REGISTER_SERVER:
                new SignUpServers().executeCommand(event);
               /* if(event.getMember().getId().equals(event.getGuild().getOwnerId())){

                }else{
                    defaultMessage(event);
                }*/
                break;
            case ISSUE_STRIKE:
                new IssueAction(Action.Strike).executeCommand(event);
                break;
            case ISSUE_WARNING:
                new IssueAction(Action.Warning).executeCommand(event);
                break;
            case BAN:
                //new IssueAction(Action.Ban).executeCommand(event);
                break;
            default:
                defaultMessage(event);
        }
    }

    private static void defaultMessage(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.RED);
        embedBuilder.setDescription("**You are not authorized to run this command.**");
        embedBuilder.setFooter(event.getGuild().getName(), event.getGuild().getIconUrl());
        event.replyEmbeds(embedBuilder.build()).setEphemeral(true).queue();
    }
}

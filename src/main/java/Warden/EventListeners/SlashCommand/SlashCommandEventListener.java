package Warden.EventListeners.SlashCommand;

import Warden.EventListeners.SlashCommand.Commands.CreateToken;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class SlashCommandEventListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        switch (event.getName()){
            case "create_token":
                if(event.getMember().getId().equals("199707685459984384")){
                    new CreateToken().executeCommand(event);
                }else event.reply("You are not authorized, please ping an admin for help! (This is a staff command)").setEphemeral(true).queue();
                break;
            default:
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setColor(Color.RED);
                embedBuilder.setDescription("**You are not authorized to run this command.**");
                embedBuilder.setFooter(event.getGuild().getName(),event.getGuild().getIconUrl());
                event.replyEmbeds(embedBuilder.build()).setEphemeral(true).queue();
        }
    }
}

package Warden.EventListeners.SlashCommand;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import javax.annotation.Nullable;
import java.awt.*;

public abstract class CommandStrategy{
    public abstract void executeCommand(SlashCommandInteractionEvent event);

    /**
     * Set title, description and fields
     * @return preparedCustom message to be passed in craeteResponse
     */
    public abstract EmbedBuilder prepareEmbedMessage();

    protected MessageEmbed createResponse(GenericCommandInteractionEvent event, @Nullable String description, EmbedBuilder embedBuilder){
        embedBuilder.setColor(Color.RED);
        embedBuilder.setDescription(description);
        embedBuilder.setFooter(event.getGuild().getName(),event.getGuild().getIconUrl());
        embedBuilder.setAuthor("Warden!");
        return embedBuilder.build();
    }
}

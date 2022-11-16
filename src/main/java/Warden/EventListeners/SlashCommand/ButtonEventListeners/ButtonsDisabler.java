package Warden.EventListeners.SlashCommand.ButtonEventListeners;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public interface ButtonsDisabler {
    default void disableButton(ButtonInteractionEvent event){
        event.getMessage().getActionRows().forEach(row -> {
            event.editComponents(row.asDisabled()).queue();
        });
    }
}

package Warden.EventListeners.SlashCommand.ButtonEventListeners;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public interface ButtonHandler {
    void executeButtonCommand(ButtonInteractionEvent event);
}

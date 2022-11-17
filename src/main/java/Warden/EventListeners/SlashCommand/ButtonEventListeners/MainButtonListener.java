package Warden.EventListeners.SlashCommand.ButtonEventListeners;

import Warden.EventListeners.ResourceBundle;
import Warden.Server.NotificationSystem.Publisher;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MainButtonListener extends ListenerAdapter implements ResourceBundle {
    private final Publisher publisher;

    public MainButtonListener(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        disableButton(event);
        String label = event.getButton().getLabel();
        ButtonHandler buttonHandler;

        if(label.equals(ACCEPT_STRING) || label.equals(DECLINE_STRING)){
            buttonHandler = new ServerRegistrationButtonListener();
            buttonHandler.executeButtonCommand(event);
        }else if(label.equals(NOTIFY_MEMBERS)){
            buttonHandler = new ActionPublisherHandler(publisher);
            buttonHandler.executeButtonCommand(event);
        }
    }

    private void disableButton(ButtonInteractionEvent event) {
        event.getMessage().getActionRows().forEach(row -> {
            event.editComponents(row.asDisabled()).queue();
        });
    }

}

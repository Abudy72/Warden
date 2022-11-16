package Warden.EventListeners.SlashCommand.ButtonEventListeners;

import Warden.EventListeners.ResourceBundle;
import Warden.Members.Actions.ActionsDao;
import Warden.Members.Actions.MemberActions;
import Warden.Server.NotificationSystem.Publisher;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class ActionPublisherHandler implements  ResourceBundle {
    private final Publisher publisher;

    public ActionPublisherHandler(Publisher publisher) {
        this.publisher = publisher;
    }

    public void sendInitialNotification(@NotNull ButtonInteractionEvent event) {
        if(event.getButton().getLabel().equals(NOTIFY_MEMBERS)){
            ActionsDao actionsDao = new ActionsDao();
            long actionId = Long.parseLong(event.getButton().getId());
            MemberActions action = actionsDao.get(actionId).get();
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setColor(Color.GREEN);
            embedBuilder.setTitle("New Notification");
            embedBuilder.setAuthor("Warden's Network. [" + action.getGuild_id() + "]");
            String msgContent = "Action Details: (issued to <@" + action.getMemberId() + ">)" +
                    "\nType: " + action.getActionType().toString() +
                    "\nIssued By: " + action.getAppliedBy() +
                    "\nReason: " + action.getReason();
            embedBuilder.setDescription(msgContent);
            embedBuilder.setFooter("Action ID: " + action.getActionId() + " | " + action.getDate());
            publisher.publishNewAction(event.getJDA(), embedBuilder.build());
        }
    }
}

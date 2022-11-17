package Warden.EventListeners.SlashCommand.ButtonEventListeners;

import Warden.Members.Actions.ActionsDao;
import Warden.Members.Actions.MemberActions;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

import static Warden.Main.Driver.getMyLogger;

public class CopyActionFromNetwork implements ButtonHandler{
    @Override
    public void executeButtonCommand(ButtonInteractionEvent event) {
        try{
            long actionId = Long.parseLong(event.getButton().getId());
            ActionsDao actionsDao = new ActionsDao();
            MemberActions memberActions = actionsDao.get(actionId).get();

            MemberActions newAction = new MemberActions(
                    memberActions.getMemberId(),
                    memberActions.getActionId(),
                    memberActions.getActionType(),
                    memberActions.getDate(),
                    memberActions.getAppliedBy(),
                    event.getGuild().getIdLong(),
                    memberActions.getReason()
            );
            actionsDao.save(newAction);
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("Action Issued!");
            event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
        }catch (Exception e){
            getMyLogger().warn(e.getMessage());
            e.printStackTrace();
            event.reply(e.getMessage()).setEphemeral(true).queue();
        }

    }
}

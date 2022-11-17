package Warden.EventListeners.SlashCommand.Commands;

import Warden.EventListeners.ResourceBundle;
import Warden.EventListeners.SlashCommand.CommandStrategy;
import Warden.Members.Actions.ActionsDao;
import Warden.Members.Actions.MemberActions;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.List;
import java.util.stream.Collectors;

public class FetchFlaggedMembers extends CommandStrategy implements ResourceBundle {
    @Override
    public void executeCommand(SlashCommandInteractionEvent event) {
        long guild_id = event.getGuild().getIdLong();
        Long memberId = event.getOption(MEMBER) != null ? event.getOption(MEMBER).getAsLong() : null;

        ActionsDao actionsDao = new ActionsDao();
        List<MemberActions> actionsList = actionsDao.getAll();
        StringBuilder finalResult = new StringBuilder();
        EmbedBuilder embedBuilder = this.prepareEmbedMessage();
        if(memberId == null){
            List<MemberActions> guildActions = actionsList.stream().filter(action -> action.getGuild_id() == guild_id).collect(Collectors.toList());
            for(MemberActions m: guildActions){
                finalResult.append(m.toString()).append('\n').append('\n');
            }
        }else{
            embedBuilder.setTitle("Member offenses [Private pool]");
            List<MemberActions> memberActions = actionsList.stream().filter(action -> action.getMemberId() == memberId).collect(Collectors.toList());
            for(MemberActions m: memberActions){
                finalResult.append(m.toString()).append('\n').append('\n');
            }
        }
        event.replyEmbeds(createResponse(event,finalResult.toString(),embedBuilder)).queue();
    }

    @Override
    public EmbedBuilder prepareEmbedMessage() {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Last 15 issued actions in this server.");
        embedBuilder.setAuthor("Warden's Network");
        return embedBuilder;
    }
}

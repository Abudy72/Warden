package Warden.EventListeners.SlashCommand.Commands;

import Warden.EventListeners.ResourceBundle;
import Warden.EventListeners.SlashCommand.CommandStrategy;
import Warden.Members.Actions.Action;
import Warden.Members.Actions.ActionManager;
import Warden.Members.Actions.MemberActions;
import Warden.Members.Member;
import Warden.Members.MembersDao;
import Warden.Server.NotificationSystem.Publisher;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.sql.Timestamp;
import java.util.Optional;

import static Warden.Main.Driver.getMyLogger;

public class IssueAction extends CommandStrategy implements ResourceBundle {

    private final Action action;
    private final Publisher publisher;

    public IssueAction(Action action, Publisher publisher) {
        this.action = action;
        this.publisher = publisher;
    }

    @Override
    public void executeCommand(SlashCommandInteractionEvent event) {
        MembersDao membersDao = new MembersDao();
        MemberActions memberActions = createMemberAction(event,membersDao);
        if(ActionManager.issueAction(memberActions,membersDao)){
            EmbedBuilder embedBuilder = prepareEmbedMessage();
            MessageEmbed msg = createResponse(event,"Action Details (issued to <@" +event.getOption(MEMBER).getAsUser().getIdLong() +">)\n" + memberActions,embedBuilder);
            event.replyEmbeds(msg).queue();
            publisher.publishNewAction(event.getJDA(), msg);
        }
    }

    private MemberActions createMemberAction(SlashCommandInteractionEvent event,MembersDao membersDao){
        try{
            //Extracting important details from slash command
            User reportedUser = event.getOption(MEMBER).getAsUser();
            long appliedBy = event.getMember().getIdLong();
            String reason = event.getOption(REASON).getAsString();

            Member member = saveIfNewMember(reportedUser,membersDao); //Saving member if its a new member

            MemberActions memberActions = new MemberActions(
                    member,
                    reportedUser.getIdLong(),
                    action,
                    new Timestamp(System.currentTimeMillis()),
                    appliedBy,
                    event.getGuild().getIdLong(),
                    reason
            );
            return memberActions;
        }catch (Exception e){
            getMyLogger().warn(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    private Member saveIfNewMember(User reportedUser, MembersDao membersDao){
        Optional<Member> member = membersDao.get(reportedUser.getIdLong());
        if(member.isPresent()){
            return member.get();
        }else {
            membersDao.save(new Member(reportedUser.getIdLong()));
            return new Member(reportedUser.getIdLong());
        }
    }
    @Override
    public EmbedBuilder prepareEmbedMessage() {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Action issued successfully!");
        return embedBuilder;
    }
}

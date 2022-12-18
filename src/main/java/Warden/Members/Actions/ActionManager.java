package Warden.Members.Actions;

import Warden.EventListeners.ResourceBundle;
import Warden.Members.MembersDao;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class ActionManager implements ResourceBundle {
    public static boolean issueAction(SlashCommandInteractionEvent event,MemberActions memberActions, MembersDao membersDao){
        if(memberActions.getActionType().equals(Action.Strike)){
            memberActions.setStrikes(memberActions.getStrikes()+1);
        }else if(memberActions.getActionType().equals(Action.Warning)){
            memberActions.setWarnings(memberActions.getWarnings()+1);
        }else {
            memberActions.setBans(memberActions.getBans()+1);
            banMember(event);
        }
        membersDao.update(memberActions);
        ActionsDao actionsDao = new ActionsDao();
        return actionsDao.save(memberActions);
    }

    private static void banMember(SlashCommandInteractionEvent event){
        int delDays = event.getOption(DAYS) != null ? event.getOption("days").getAsInt() : 0;
        Guild guild = event.getGuild();
        String reason = event.getOption(REASON).getAsString();
        long memberId = event.getOption(MEMBER).getAsLong();
        //banning Player
        try{
            guild.ban(UserSnowflake.fromId(memberId),delDays,reason).queue();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

package Warden.Members.Actions;

import Warden.EventListeners.ResourceBundle;
import Warden.Members.MembersDao;

public class ActionManager implements ResourceBundle {
    public static boolean issueAction(MemberActions memberActions, MembersDao membersDao){
        if(memberActions.getActionType().equals(Action.Strike)){
            memberActions.setStrikes(memberActions.getStrikes()+1);
        }else if(memberActions.getActionType().equals(Action.Warning)){
            memberActions.setWarnings(memberActions.getWarnings()+1);
        }else {
            memberActions.setBans(memberActions.getBans()+1);
        }
        membersDao.update(memberActions);
        ActionsDao actionsDao = new ActionsDao();
        return actionsDao.save(memberActions);
    }
}

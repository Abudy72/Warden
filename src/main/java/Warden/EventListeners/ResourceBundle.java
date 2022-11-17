package Warden.EventListeners;

public interface ResourceBundle {
    // ------------- Command Names -------------
    String CREATE_TOKEN = "create_token";
    String GET_TOKENS = "get_tokens";
    String REGISTER_SERVER = "register_server";
    String ISSUE_STRIKE = "issue_strike";
    String ISSUE_WARNING = "issue_warning";
    String BAN = "ban";
    String WATCHLIST = "watchlist";

    // ------------- Command Parameters -------------
    String TOKEN_ID = "token_id";
    String MEMBER = "member";
    String REASON = "reason";


    // ------------- ErrorCodes and ErrorMessages -------------
    String TOKEN_ERR1 = "TOKEN_ERR1";
    String TOKEN_ERR1_MSG = "Invalid token, please contact Discord Restoration for assistance. You can find an invite in my *about* section";
    String TOKEN_ERR2 = "TOKEN_ERR2";
    String TOKEN_ERR2_MSG = "Token was already used, please contact Discord Restoration for assistance. You can find an invite in my *about* section";
    String REGISTRATION_ERR = "REGISTRATION_ERR";
    String REGISTRATION_ERR_MSG = "Unknown Error, please contact bot owner.";

    // ------------- Entity Names -------------
    String NETWORK_CATEGORY = "network_category"; //Category
    String NETWORK_ANNOUNCEMENTS = "network_announcements"; //Channel
    String NETWORK_MEMBER = "network_member"; //Role

    // ------------- Misc. -------------
    String NOTIFY_MEMBERS = "Notify Network partners?";
    static final String ACCEPT_STRING = "✅ Accept";
    static final String DECLINE_STRING = "❌ Decline";
}

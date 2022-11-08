package Warden.Main;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

import static Warden.Main.Driver.getMyLogger;

public class DiscordStarterImpl implements DiscordStarter {
    @Override
    public void start() {
        String token  = "OTI0NDAxNDQzODcwNDgyNDYy.Gw3ecS.-we32PabifwUM5thBgTpohQOhUSJaipZJaJozc";

        JDABuilder jdaBuilder = JDABuilder.createDefault(token);
        jdaBuilder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        jdaBuilder.setBulkDeleteSplittingEnabled(false);
        jdaBuilder.setCompression(Compression.NONE);
        jdaBuilder.setActivity(Activity.watching("over your server."));

        //add event listeners

        try {

            JDA jda = jdaBuilder.build();

        }catch (LoginException exception){
            getMyLogger().fatal(exception.getMessage());
            exception.printStackTrace();
        }
    }
}

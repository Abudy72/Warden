package Warden.Main;

import Warden.EventListeners.SlashCommand.SlashCommandEventListener;
import Warden.WardenCommands.Generator.CommandGenerator;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.apache.logging.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;

import static Warden.Main.Driver.getMyLogger;

public class DiscordStarterImpl implements DiscordStarter {
    private final Logger logger = LoggerFactory.getLogger(DiscordStarterImpl.class);
    @Override
    public void start() {
        String token  = System.getenv("DISCORD_BOT_WARDEN");

        JDABuilder jdaBuilder = JDABuilder.createDefault(token);
        jdaBuilder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        jdaBuilder.setBulkDeleteSplittingEnabled(false);
        jdaBuilder.setCompression(Compression.NONE);
        jdaBuilder.setActivity(Activity.watching("over your server."));
        //add event listeners
        jdaBuilder.addEventListeners(new SlashCommandEventListener());
        try {
            getMyLogger().log(Level.INFO,"JDA loaded!, loading commands.");
            JDA jda = jdaBuilder.build();
            jda.updateCommands().addCommands(CommandGenerator.loadCommands()).queue();
            throw new LoginException("SIKE BITCH");
        }catch (LoginException exception){
            exception.printStackTrace();
            logger.warn("SIKE BITCH");
            getMyLogger().fatal("SIKE BBTICH");
        }
    }
}

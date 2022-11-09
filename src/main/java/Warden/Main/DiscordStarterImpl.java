package Warden.Main;

import Warden.EventListeners.SlashCommand.SlashCommandEventListener;
import Warden.WardenCommands.Generator.CommandGenerator;
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
            JDA jda = jdaBuilder.build();
            getMyLogger().info("JDA loaded!, loading commands.");
            jda.updateCommands().addCommands(CommandGenerator.loadCommands()).queue();
        }catch (LoginException exception){
            getMyLogger().fatal(exception.getMessage());
            exception.printStackTrace();
        }
    }
}

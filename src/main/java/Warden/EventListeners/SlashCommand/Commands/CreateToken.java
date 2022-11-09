package Warden.EventListeners.SlashCommand.Commands;

import Warden.EventListeners.SlashCommand.CommandStrategy;
import Warden.Server.Authorization.Token;
import Warden.Server.Authorization.TokensDao;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

import static Warden.Main.Driver.getMyLogger;

public class CreateToken extends CommandStrategy {
    private final Logger myLogger = getMyLogger();
    private final Token token = new Token();
    private final List<Token> tokenList = new LinkedList<>();
    @Override
    public void executeCommand(SlashCommandInteractionEvent event) {
        TokensDao tokensDao = new TokensDao();
        myLogger.info("Creating new token...");
        tokensDao.save(token);
        myLogger.info("Token saved!");
        tokenList.addAll(tokensDao.getAll());

        MessageEmbed messageEmbed = createResponse(event,"New Token has been created and saved! Below you will find details about available tokens",prepareEmbedMessage());
        event.replyEmbeds(messageEmbed).setEphemeral(true).queue();
    }
    @Override
    public EmbedBuilder prepareEmbedMessage() {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("New token was created successfully!");
        embedBuilder.addField(new Field("New Token:","token: " + token.getToken() + ":" + token.getTokenId(),true));
        int redeemedTokens = 0;
        int availableTokens = 0;
        StringBuilder tokens = new StringBuilder();

        for(Token t: tokenList){
            if(t.isClaimed()) redeemedTokens++;
            else {
                availableTokens++;
                tokens.append("\uD83E\uDE99 **Token: ").append(t.getToken()).append("** \uD83C\uDD94**id: ").append(t.getTokenId()).append("**\n");
            }

        }
        embedBuilder.addField(new Field("Tokens Details:","**Redeemed Tokens**: " + redeemedTokens + "\n**Available Tokens** " + availableTokens,true));
        embedBuilder.addField(new Field("List of available tokens:",tokens.toString(),false));
        return embedBuilder;
    }
}

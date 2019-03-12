package view;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class View extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = "NTQ3MDQyNjYxOTMzMjUyNjU3.D0183Q.y3HEU65PCiy9b-EHC-QORCdXJ00";
        builder.setToken(token);
        builder.addEventListener(new View());
        builder.buildAsync();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        String message = event.getMessage().getContentRaw();

        if (message.equals("!start")) {
            event.getChannel().sendMessage("Hello, I am CryptoBot.").queue();
            event.getChannel().sendMessage("How can I help you?").queue();
            event.getChannel().sendMessage("!getLastest: Get a paginated list of all cryptocurrencies with latest market data.").queue();

        }

    }

}
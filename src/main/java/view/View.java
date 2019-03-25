package view;

import controller.ControllerSearchBasic;
import controller.ControllerSearchComplete;
import model.CoinModel;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class View extends ListenerAdapter {
    private ControllerSearchBasic basic = new ControllerSearchBasic();
    private ControllerSearchComplete complete = new ControllerSearchComplete();
    private CoinModel coin;

    public static void main(String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        final String token = "NTQ3MDQyNjYxOTMzMjUyNjU3.D0183Q.y3HEU65PCiy9b-EHC-QORCdXJ00";
        builder.setToken(token);
        builder.addEventListener(new View());
        builder.buildAsync();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        String message = event.getMessage().getContentRaw();

        if (message.startsWith("!start")) {
            event.getChannel().sendMessage("Hello, I am CryptoBot.").queue();
            event.getChannel().sendMessage("How can I help you?").queue();
            event.getChannel().sendMessage("Basic + Coin: Get name, symbol, rank and current price of the coin.").queue();
            event.getChannel().sendMessage("Complete + Coin: Get name, symbol, circulatingSupply, rank," +
                    " price, percentChange1h, percentChange24h, percentChange7d, maxSupply").queue();

        }
        else if (message.split(" ")[0].toLowerCase().equals("basic")){

            coin = basic.search(message.split(" ")[1]);

            event.getChannel().sendMessage("Name: "+ coin.getName()+" - "+coin.getSymbol()).queue();
            event.getChannel().sendMessage("Rank: "+ coin.getRank()).queue();
            event.getChannel().sendMessage("Price: "+ coin.getPrice()).queue();

            event.getJDA().removeEventListener(this);
        }
        else if (message.split(" ")[0].toLowerCase().equals("complete")){

            coin = complete.search(message.split(" ")[1]);

            event.getChannel().sendMessage("Name: "+ coin.getName()+" - "+coin.getSymbol()).queue();
            event.getChannel().sendMessage("Rank: "+ coin.getRank()).queue();
            event.getChannel().sendMessage("Price: "+ coin.getPrice()).queue();
            event.getChannel().sendMessage("Circulating Supply: "+ coin.getCirculatingSupply()).queue();
            event.getChannel().sendMessage("Max Supply: "+ coin.getMaxSupply()).queue();
            event.getChannel().sendMessage("Percent Change 1h: "+ coin.getPercentChange1h()).queue();
            event.getChannel().sendMessage("Percent Change 24h: "+ coin.getPercentChange24h()).queue();
            event.getChannel().sendMessage("Percent Change 7d: "+ coin.getPercentChange7d()).queue();

        }
    }

}
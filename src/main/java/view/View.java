package view;

import controller.ControllerSeachInfo;
import controller.ControllerSearchBasic;
import controller.ControllerSearchComplete;
import model.Coin;
import model.Info;
import model.Model;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class View extends ListenerAdapter implements Observer {
    private ControllerSearchBasic basic;
    private ControllerSearchComplete complete;
    private ControllerSeachInfo infoSearch;
    private Coin coin;
    private Info info;
    private Model model;

    public View(Model model) throws LoginException {
        this.model = model;

        JDABuilder builder = new JDABuilder(AccountType.BOT);
        final String token = "NTQ3MDQyNjYxOTMzMjUyNjU3.D0183Q.y3HEU65PCiy9b-EHC-QORCdXJ00";
        builder.setToken(token);
        builder.addEventListener(this);
        builder.buildAsync();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        String message = event.getMessage().getContentRaw();

        if(event.getAuthor().isBot())

        return;
        if (message.startsWith("!start")) {
            event.getChannel().sendMessage("Hello, I am CryptoBot.\n" +
                    "How can I help you?\n\n" +
                    "Basic + Coin: Get name, symbol, rank and current price of the coin.\n" +
                    "Complete + Coin: Get name, symbol, circulatingSupply, rank," +
                    "price, percentChange1h, percentChange24h, percentChange7d, maxSupply\n" +
                    "Info + Symbol: Get name, symbol, category, logo, tags, description").queue();
        }
        else if (message.split(" ")[0].toLowerCase().equals("basic")) {

            basic = new ControllerSearchBasic(model, this);
            basic.search(message.split(" ")[1]);

            event.getChannel().sendMessage("Name: "+ coin.getName()+" - "+ coin.getSymbol()+
                    "\nRank: "+ coin.getRank()+
                    "\nPrice: $"+ coin.getPrice()).queue();

        }

        else if (message.split(" ")[0].toLowerCase().equals("complete")) {

            complete = new ControllerSearchComplete(model, this);
            complete.search(message.split(" ")[1]);


            event.getChannel().sendMessage("Name: "+ coin.getName()+" - "+coin.getSymbol()+
                    "\nRank: "+ coin.getRank()+
                    "\nPrice: $"+ coin.getPrice()+
                    "\nCirculating Supply: "+ coin.getCirculatingSupply()+
                    "\nMax Supply: "+ coin.getMaxSupply()+
                    "\nPercent Change 1h: "+ coin.getPercentChange1h()+"%"+
                    "\nPercent Change 24h: "+ coin.getPercentChange24h()+"%"+
                    "\nPercent Change 7d: "+ coin.getPercentChange7d()+"%").queue();

        }

        else if (message.split(" ")[0].toLowerCase().equals("info")) {

            infoSearch = new ControllerSeachInfo(model, this);
            infoSearch.search(message.split(" ")[1]);

            event.getChannel().sendMessage("Name: "+ info.getName()+" - "+info.getSymbol()+
                    "\nCategory: "+ info.getCategory()+
                    "\nTags: "+ info.getTags()+
                    "\nDescription:\n"+ info.getDescription()+
                    "\n\nLogo: "+ info.getLogo()).queue();
        }
    }


    @Override
    public void update(Coin coin) {
        this.coin = coin;
    }

    @Override
    public void update(Info info) {
        this.info = info;
    }

}
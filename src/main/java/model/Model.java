package model;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import view.Observer;

public class Model implements Subject {
    private Observer observer;
    private Coin coin = null;
    private Info info = null;
    private API api =  new API();
    static ObjectContainer basicCoins = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "database/basicCoins.db4o");
    static ObjectContainer completeCoins = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "database/completeCoins.db4o");


    private static Model instance;

    private Model(){}

    public static Model getInstance() {
        if(instance == null){
            instance = new Model();
        }
        return instance;
    }

    @Override
    public void registerObserver (Observer observer) {
        this.observer = observer;
    }

    @Override
    public void notifyObserver (Coin coin) {
        observer.update(coin);
    }

    @Override
    public void notifyObserver(Info info) {
        observer.update(info);
    }

    public void searchBasic (String name){
        api.getBasic();

        coin = findCoin(name, 1);

        this.notifyObserver(coin);
    }

    public void searchComplete (String name){
        api.getComplete();

        coin = findCoin(name, 2);

        this.notifyObserver(coin);
    }

    public void searchInfo (String name) {
        info = api.getInfo(name);

        this.notifyObserver(info);
    }

    private Coin findCoin (String name, int kind) {
        if (kind == 1){
            Query basicQuery = basicCoins.query();
            basicQuery.constrain(Coin.class);
            ObjectSet<Coin> allBasicCoin = basicQuery.execute();
            for (Coin coin : allBasicCoin){
                if (coin.getName().toLowerCase().equals(name.toLowerCase()))
                    return  coin;
            }
        }
        else {
            Query completeQuery = completeCoins.query();
            completeQuery.constrain(Coin.class);
            ObjectSet<Coin> allCompleteCoin = completeQuery.execute();
            for (Coin coin : allCompleteCoin){
                if (coin.getName().toLowerCase().equals(name.toLowerCase()))
                    return  coin;
            }
        }
        return new Coin("Not found", "0", "0", "0");
    }

}

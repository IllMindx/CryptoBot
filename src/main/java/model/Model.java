package model;

import view.Observer;

import java.util.LinkedList;
import java.util.List;

public class Model implements Subject {
    private Observer observer;
    private static CoinModel coin = null;
    private static API api =  new API();
    private static Model model = new Model();


    @Override
    public void registerObserver (Observer observer) {
        System.err.println("registerObserver: "+observer);
        this.observer = observer;
    }

    @Override
    public void notifyObserver (CoinModel coin) {
        observer.update(coin);
    }

    public static void searchBasic (String name){

        coin = api.getBasic(name);

        model.notifyObserver(coin);
    }

    public static void searchComplete (String name){
        coin = api.getComplete(name);

        model.notifyObserver(coin);
    }
}

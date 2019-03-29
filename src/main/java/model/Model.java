package model;

import view.Observer;

public class Model implements Subject {
    private Observer observer;
    private static Coin coin = null;
    private API api =  new API();

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

    public void searchBasic (String name){

        coin = api.getBasic(name);

        this.notifyObserver(coin);
    }

    public void searchComplete (String name){
        coin = api.getComplete(name);

        this.notifyObserver(coin);
    }
}

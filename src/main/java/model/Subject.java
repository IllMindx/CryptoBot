package model;

import view.Observer;

public interface Subject {
    void registerObserver (Observer observer);

    void notifyObserver (CoinModel coin);
}

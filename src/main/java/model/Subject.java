package model;

import view.Observer;

public interface Subject {
    void registerObserver (Observer observer);

    void notifyObserver (Coin coin);

    void notifyObserver (Info info);
}

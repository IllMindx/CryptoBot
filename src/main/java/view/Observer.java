package view;

import model.Coin;

public interface Observer {
    void update(Coin coin);
}

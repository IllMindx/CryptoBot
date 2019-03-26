package view;

import model.CoinModel;

public interface Observer {
    void update(CoinModel coin);
}

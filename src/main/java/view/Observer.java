package view;

import model.Coin;
import model.Info;

public interface Observer {
    void update(Coin coin);

    void update(Info info);
}

package controller;

import model.API;
import model.CoinModel;

public interface ControllerSearch {

    API api = new API();

    CoinModel search(String name);
}

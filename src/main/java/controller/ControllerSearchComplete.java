package controller;

import model.CoinModel;

public class ControllerSearchComplete implements ControllerSearch{
    @Override
    public CoinModel search(String name) {
        return api.getComplete(name);
    }
}

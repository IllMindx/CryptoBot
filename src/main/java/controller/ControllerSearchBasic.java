package controller;

import model.CoinModel;

public class ControllerSearchBasic implements ControllerSearch {

    @Override
    public CoinModel search(String name) {
        return api.getBasic(name);
    }
}

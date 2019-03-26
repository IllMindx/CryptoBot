package controller;

import model.CoinModel;

public class ControllerSearchBasic implements ControllerSearch {

    @Override
    public void search(String name) {
        CoinModel.searchBasic(name);
    }
}

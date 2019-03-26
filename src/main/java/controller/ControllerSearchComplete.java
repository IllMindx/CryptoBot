package controller;

import model.CoinModel;

public class ControllerSearchComplete implements ControllerSearch{
    @Override
    public void search(String name) {
        CoinModel.searchComplete(name);
    }
}

package controller;

import model.Model;

public class ControllerSearchBasic implements ControllerSearch {

    @Override
    public void search(String name) {
        Model.searchBasic(name);
    }
}

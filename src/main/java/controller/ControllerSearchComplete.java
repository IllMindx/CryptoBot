package controller;

import model.Model;

public class ControllerSearchComplete implements ControllerSearch{
    @Override
    public void search(String name) {
        Model.searchComplete(name);
    }
}

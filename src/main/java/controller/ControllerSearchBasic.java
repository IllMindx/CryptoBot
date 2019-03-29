package controller;

import model.Model;
import view.View;

public class ControllerSearchBasic implements ControllerSearch {

    private Model model;
    private View view;

    public ControllerSearchBasic(Model model, View view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void search(String name) {
        model.searchBasic(name);
    }
}

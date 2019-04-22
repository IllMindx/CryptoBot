package controller;

import model.Model;
import view.View;

public class ControllerSeachInfo implements ControllerSearch {
    private Model model;
    private View view;

    public ControllerSeachInfo(Model model, View view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void search(String name) {
        model.searchInfo(name);
    }
}

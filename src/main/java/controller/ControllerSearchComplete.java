package controller;

import model.Model;
import view.View;

public class ControllerSearchComplete implements ControllerSearch{

    private Model model;
    private View view;

    public ControllerSearchComplete(Model model, View view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void search(String name) {
        model.searchComplete(name);
    }
}

package ru.develop.weather.scene;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainSceneController {

    public MainSceneController() {
    }

    @FXML
    private Label label;

    @FXML
    protected void onButtonExit() {
        System.exit(0);
    }
}

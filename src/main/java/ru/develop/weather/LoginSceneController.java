package ru.develop.weather;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static ru.develop.weather.StaticEnvorements.*;

public class LoginSceneController implements Initializable {

    @FXML
    private ChoiceBox<TypeWeatherRequest> choiceBoxType;

    @FXML
    private ChoiceBox<String> cbCity;

    @FXML
    private Button loginExitButton;

    @FXML
    private Button loginNextButton;

    @FXML
    private TextField textUserApiKey;

    @FXML
    void selectChoiceType(ActionEvent event) {
        TYPE_REQUEST = choiceBoxType.getValue();
        switch (choiceBoxType.getValue()) {
            case Astro, CivilLight -> textUserApiKey.disableProperty().set(true);
            case Yandex -> textUserApiKey.disableProperty().set(false);
        }
    }

    @FXML
    void selectChoiceCity(ActionEvent event) {
        CITY_NAME = cbCity.getValue();
        CHOOSE_CITY_COORDINATES.clear();
        CHOOSE_CITY_COORDINATES.add(CITY_COORDINATES.get(CITY_NAME).get(0));
        CHOOSE_CITY_COORDINATES.add(CITY_COORDINATES.get(CITY_NAME).get(1));
    }

    @FXML
    void btnLoginExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void btnNextScene(ActionEvent event) throws IOException {
        if ((choiceBoxType.getValue().equals(TypeWeatherRequest.Yandex)) && (textUserApiKey.getLength() == 0)) {
            Alert alert = new Alert(Alert.AlertType.NONE, "For yandex weather API Key must be not null",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }
        String windowType = "";
        switch (choiceBoxType.getSelectionModel().getSelectedItem()) {
            case Civil -> windowType = "CivilWindow.fxml";
            case CivilLight -> windowType = "CivilLightWindow.fxml";
            case Astro -> windowType = "AstroWindow.fxml";
            case Yandex -> windowType = "YandexWindow.fxml";

        }
        Stage stage;
        Parent root;
        stage = (Stage) loginNextButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource(windowType));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<TypeWeatherRequest> values = new ArrayList<>();
        values.add(TypeWeatherRequest.Civil);
        values.add(TypeWeatherRequest.CivilLight);
        ObservableList<TypeWeatherRequest> listType = FXCollections.observableArrayList(values);
        for (TypeWeatherRequest t : listType) {
            choiceBoxType.getItems().add(t);
        }
        choiceBoxType.setOnAction(this::selectChoiceType);
        choiceBoxType.getSelectionModel().selectFirst();
        ObservableList<String> listCity = FXCollections.observableArrayList(CITY_COORDINATES.keySet());
        for (String c : listCity) {
            cbCity.getItems().add(c);
        }
        cbCity.setOnAction(this::selectChoiceCity);
        cbCity.getSelectionModel().selectFirst();
    }
}

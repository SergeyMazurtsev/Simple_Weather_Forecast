package ru.develop.weather;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.develop.weather.client.WeatherClient;
import ru.develop.weather.client.dto.WeatherDto;
import ru.develop.weather.client.dto.free7times.astro.AstroDto;

import java.net.URL;
import java.util.ResourceBundle;

import static ru.develop.weather.StaticEnvorements.TYPE_REQUEST;

public class AstroSceneController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WeatherClient client = new WeatherClient();
        ResponseEntity<WeatherDto> response = client.getWeatherData(TYPE_REQUEST);
        if (response.getBody() != null) {
            responseAstroData((AstroDto) response.getBody());
        } else {
            errorResponseData(response.getStatusCode());
        }
    }

    private void responseAstroData(AstroDto response) {
        System.out.println(response);
    }

    private void errorResponseData(HttpStatus status) {
        Alert alert = new Alert(Alert.AlertType.NONE, "Error getting data from server, code = " + status, ButtonType.CLOSE);
        alert.showAndWait();
    }
}

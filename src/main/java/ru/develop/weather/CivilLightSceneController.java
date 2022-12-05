package ru.develop.weather;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.develop.weather.client.WeatherClient;
import ru.develop.weather.client.dto.WeatherDto;
import ru.develop.weather.client.dto.free7times.civillight.CivilLightDataSeries;
import ru.develop.weather.client.dto.free7times.civillight.CivilLightDto;
import ru.develop.weather.client.dto.free7times.civillight.CivilLightWeatherType;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import static ru.develop.weather.StaticEnvorements.CITY_NAME;
import static ru.develop.weather.StaticEnvorements.TYPE_REQUEST;

@Slf4j
public class CivilLightSceneController implements Initializable {
    private SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

    @FXML
    private GridPane gpDataSet;

    @FXML
    private Label lbCity;


    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WeatherClient client = new WeatherClient();
        ResponseEntity<WeatherDto> response = client.getWeatherData(TYPE_REQUEST);
        if (response.getBody() != null) {
            responseCivilLightData((CivilLightDto) response.getBody());
        } else {
            errorResponseData(response.getStatusCode());
        }
    }

    private void responseCivilLightData(CivilLightDto response) throws ParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE dd.MM");
        lbCity.setText(CITY_NAME);
        log.info("Get response civil from server dto = {}", response.getProduct());
        List<CivilLightDataSeries> data = response.getDataseries();
        for (int row = 1; row < gpDataSet.getRowCount(); row++) {
            for (int col = 0; col < gpDataSet.getColumnCount(); col++) {
                Label node = new Label();
                setLabelProperties(node);
                gpDataSet.add(node, col, row);
                int rowInData = row - 1;
                LocalDate dateRow = LocalDate.ofInstant(df.parse(data.get(rowInData).getDate()).toInstant(), ZoneId.systemDefault());
                switch (col) {
                    case 0 -> node.setText(dateTimeFormatter.format(dateRow));
                    case 1 -> {
                        node.setGraphic(getImageOfWeather(data.get(rowInData).getWeather()));
                        node.setTooltip(new Tooltip(data.get(rowInData).getWeather().getTitle()));
                    }
                    case 2 -> node.setText(data.get(rowInData).getTemp2m().getMin().toString());
                    case 3 -> {
                        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/gif/icons8-thermometer-100.png")));
                        imageView.setFitHeight(30);
                        imageView.setFitWidth(30);
                        node.setGraphic(imageView);
                        node.setAlignment(Pos.CENTER);
                    }
                    case 4 -> node.setText(data.get(rowInData).getTemp2m().getMax().toString());
                    case 5 -> node.setText(data.get(rowInData).getWind10m_max().toString());
                }
            }
        }
    }

    private void setLabelProperties(Label label) {
        label.setTextFill(Paint.valueOf("WHITE"));
        label.setFont(new Font("Times New Roman", 16));
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);
        label.setGraphicTextGap(4d);
    }

    private void errorResponseData(HttpStatus status) {
        Alert alert = new Alert(Alert.AlertType.NONE, "Error getting data from server, code = " + status, ButtonType.CLOSE);
        alert.showAndWait();
    }

    private ImageView getImageOfWeather(CivilLightWeatherType weatherType) {
        Image image = null;
        switch (weatherType) {
            case clear -> image = new Image(getClass().getResourceAsStream("/gif/icons8-sun-100.png"));
            case mcloudy -> image = new Image(getClass().getResourceAsStream("/gif/icons8-partly-cloudy-day-100.png"));
            case cloudy -> image = new Image(getClass().getResourceAsStream("/gif/icons8-cloud-100.png"));
            case rain -> image = new Image(getClass().getResourceAsStream("/gif/icons8-rain-100.png"));
            case snow -> image = new Image(getClass().getResourceAsStream("/gif/icons8-snow-100.png"));
            case ts -> image = new Image(getClass().getResourceAsStream("/gif/icons8-cloud-lightning-100.png"));
            case tsrain -> image = new Image(getClass().getResourceAsStream("/gif/icons8-storm-100.png"));
            default -> image = new Image(getClass().getResourceAsStream("/gif/icons8-thermometer-100.png"));
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        return imageView;
    }

}

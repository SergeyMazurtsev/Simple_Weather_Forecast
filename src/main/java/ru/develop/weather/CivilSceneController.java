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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.develop.weather.client.WeatherClient;
import ru.develop.weather.client.dto.WeatherDto;
import ru.develop.weather.client.dto.free7times.civil.CivilDataseries;
import ru.develop.weather.client.dto.free7times.civil.CivilDto;
import ru.develop.weather.client.dto.free7times.civil.CivilWeatherType;
import ru.develop.weather.client.dto.free7times.civil.CivilWind;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static ru.develop.weather.StaticEnvorements.*;


@Slf4j
public class CivilSceneController implements Initializable {

    public CivilSceneController() {
    }

    @FXML
    private GridPane gpDataSet;

    @FXML
    private Label lbCity;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WeatherClient client = new WeatherClient();
        ResponseEntity<WeatherDto> response = client.getWeatherData(TYPE_REQUEST);
        if (response.getBody() != null) {
            responseCivilData((CivilDto) response.getBody());
        } else {
            errorResponseData(response.getStatusCode());
        }
    }

    private void responseCivilData(CivilDto response) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE dd.MM");
        log.info("Get response civil from server dto = {}", response.getProduct());
        lbCity.setText(CITY_NAME);
        List<CivilDataseries> data = convertToWeekData(response);
        LocalDateTime start = response.getInit();
        for (int row = 1; row < gpDataSet.getRowCount(); row++) {
            for (int col = 0; col < gpDataSet.getColumnCount(); col++) {
                Label node = new Label();
                setLabelProperties(node);
                gpDataSet.add(node, col, row);
                int rowInData = row - 1;
                switch (col) {
                    case 0 ->
                            node.setText(dateTimeFormatter.format(start.plusDays(data.get(rowInData).getTimepoint())));
                    case 1 -> {
                        node.setText(data.get(rowInData).getTemp2m().toString() + DEGREE);
                        node.setFont(new Font("Times New Roman", 22));
                    }
                    case 2 -> {
                        node.setGraphic(getImageOfWeather(data.get(rowInData).getWeather()));
                        node.setAlignment(Pos.CENTER);
                        node.setTooltip(new Tooltip(data.get(rowInData).getWeather().getTitle()));
                    }
                    case 3 -> node.setText(CLOUD_COVER.get(data.get(rowInData).getCloudcover()));
                    case 4 -> node.setText(data.get(rowInData).getPrec_type());
                    case 5 -> node.setText(PRECIPITATION_AMOUNT.get(data.get(rowInData).getPrec_amount()));
                    case 6 -> node.setText(data.get(rowInData).getRh2m());
                    case 7 -> node.setText(WIND_DIRECTION.get(data.get(rowInData).getWind10m().getDirection()));
                    case 8 -> node.setText(data.get(rowInData).getWind10m().getSpeed().toString());
                }
            }
        }
    }

    private void errorResponseData(HttpStatus status) {
        Alert alert = new Alert(Alert.AlertType.NONE, "Error getting data from server, code = " + status, ButtonType.CLOSE);
        alert.showAndWait();
    }

    private List<CivilDataseries> convertToWeekData(CivilDto dto) {
        List<CivilDataseries> result = new ArrayList<>();
        LocalDateTime checkDate = dto.getInit();
        for (int day = 0; day < 8; day++) {
            List<CivilDataseries> dayDataSet = new ArrayList<>();
            for (CivilDataseries data : dto.getDataseries()) {
                if (checkDate.getDayOfMonth() == dto.getInit().plusHours(data.getTimepoint()).getDayOfMonth()) {
                    dayDataSet.add(data);
                }
            }
            if (!dayDataSet.isEmpty()) {
                CivilDataseries outData = CivilDataseries.builder().build();
                outData.setTimepoint(day);
                outData.setCloudcover((int) dayDataSet.stream().map(CivilDataseries::getCloudcover).mapToInt(a -> a).average().orElse(0.0));
                outData.setLifted_index((int) dayDataSet.stream().map(CivilDataseries::getLifted_index).mapToInt(a -> a).average().orElse(0.0));
                outData.setPrec_type((dayDataSet.stream().map(CivilDataseries::getPrec_type).anyMatch(i -> !i.equals("none"))) ? dayDataSet.stream().map(CivilDataseries::getPrec_type).toList().get(3) : "none");
                outData.setPrec_amount(dayDataSet.stream().map(CivilDataseries::getPrec_amount).mapToInt(a -> a).max().orElse(0));
                outData.setTemp2m((int) dayDataSet.stream().map(CivilDataseries::getTemp2m).mapToInt(a -> a).average().orElse(0));
                outData.setRh2m(dayDataSet.stream().map(CivilDataseries::getRh2m).findFirst().orElse(""));
                List<CivilWind> wind = dayDataSet.stream().map(CivilDataseries::getWind10m).toList();
                outData.setWind10m(CivilWind.builder()
                        .direction(wind.stream().map(CivilWind::getDirection).findFirst().orElse(""))
                        .speed(wind.stream().map(CivilWind::getSpeed).mapToInt(a -> a).max().orElse(0))
                        .build());
                int middleTime = dayDataSet.stream().map(CivilDataseries::getWeather).toList().size() / 2;
                outData.setWeather(dayDataSet.stream().map(CivilDataseries::getWeather).toList().get(middleTime));//.findAny().orElse(null));
                result.add(outData);
            }
            checkDate = checkDate.plusDays(1);
        }
        return result;
    }

    private void setLabelProperties(Label label) {
        label.setTextFill(Paint.valueOf("WHITE"));
        label.setFont(new Font("Times New Roman", 14));
        label.setTextAlignment(TextAlignment.CENTER);
        label.setGraphicTextGap(4d);
    }

    private ImageView getImageOfWeather(CivilWeatherType weatherType) {
        Image image = null;
        switch (weatherType) {
            case clearday -> image = new Image(getClass().getResourceAsStream("/gif/icons8-sun-100.png"));
            case clearnight -> image = new Image(getClass().getResourceAsStream("/gif/icons8-moon-and-stars-100.png"));
            case pcloudyday, mcloudyday ->
                    image = new Image(getClass().getResourceAsStream("/gif/icons8-partly-cloudy-day-100.png"));
            case cloudyday, cloudynight ->
                    image = new Image(getClass().getResourceAsStream("/gif/icons8-cloud-100.png"));
            case pcloudynight, mcloudynight ->
                    image = new Image(getClass().getResourceAsStream("/gif/icons8-night-100.png"));
            case humidday, humidnight, lightrainday, lightrainnight, oshowerday, oshowernight, ishowerday, ishowernight ->
                    image = new Image(getClass().getResourceAsStream("/gif/icons8-rain-100.png"));
            case lightsnowday, lightsnownight ->
                    image = new Image(getClass().getResourceAsStream("/gif/icons8-snow-100.png"));
            case rainday, rainnight ->
                    image = new Image(getClass().getResourceAsStream("/gif/icons8-snow-storm-100.png"));
            case snowday, snownight -> image = new Image(getClass().getResourceAsStream("/gif/icons8-storm-100.png"));
            case rainsnowday, rainsnownight ->
                    image = new Image(getClass().getResourceAsStream("/gif/icons8-sleet-100.png"));
            default -> image = new Image(getClass().getResourceAsStream("/gif/icons8-thermometer-100.png"));
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        return imageView;
    }

}

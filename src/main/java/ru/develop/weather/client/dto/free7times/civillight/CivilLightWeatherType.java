package ru.develop.weather.client.dto.free7times.civillight;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum CivilLightWeatherType {
    clear("Total cloud cover less than 20%"),
    mcloudy("Total cloud cover between 20%-80%"),
    cloudy("Total cloud cover over over 80%"),
    rain("Rain with total cloud cover over 80%"),
    snow("Snow with total cloud cover over 80%"),
    ts("Lifted Index less than -5"),
    tsrain("Lifted Index less than -5 with rain");

    private String title;

    CivilLightWeatherType(String title) {
        this.title = title;
    }
}

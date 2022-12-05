package ru.develop.weather.client.dto.yandex;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Forecast {
    private String date;
    private Integer date_ts;
    private Integer week;
    private String sunrise;
    private String sunset;
    private Integer moon_code;
    private String moon_text;
    private List<ForecastParts> parts;
}

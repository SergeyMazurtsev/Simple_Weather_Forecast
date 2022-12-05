package ru.develop.weather.client.dto.yandex;

import lombok.*;
import ru.develop.weather.client.dto.WeatherDto;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class YandexDto implements WeatherDto {
    private Integer now;
    private LocalDateTime now_dt;
    private Info info;
    private Fact fact;
    private Forecast forecast;

    public static final String NOW_DESCRIPTION = "Время сервера в формате Unixtime.";
    public static final String NOW_DT_DESCRIPTION = "Время сервера в UTC.";
    public static final String INFO_DESCRIPTION = "Объект содержит информацию о населенном пункте.";
    public static final String FACT_DESCRIPTION = "Объект содержит информацию о погоде на данный момент.";
    public static final String FORECAST_DESCRIPTION = "Объект содержит данные прогноза погоды.";
}

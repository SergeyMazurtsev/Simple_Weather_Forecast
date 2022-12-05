package ru.develop.weather.client.dto.free7times.civillight;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CivilLightDataSeries {
    private String date;
    private CivilLightWeatherType weather;
    private CivilLightTemperature temp2m;
    private Integer wind10m_max;

    public static final String DATE_DESCRIPTION = "Date of forecast.";
    public static final String WEATHER_DESCRIPTION = "Weather type.";
    public static final String TEMP2M_DESCRIPTION = "Maximum and minimum temperature Celsius degree.";
    public static final String WIND10M_MAX_DESCRIPTION = "Maximum wind speed by 10 m/sec.";
}

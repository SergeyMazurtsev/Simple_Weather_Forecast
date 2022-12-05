package ru.develop.weather.client.dto.yandex;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForecastParts {
    private String part_name;
    private Double temp_min;
    private Double temp_max;
    private Double temp_avg;
    private Double feels_like;
    private String icon;
    private String condition;
    private String daytime;
    private Boolean polar;
    private Double wind_speed;
    private Double wind_gust;
    private String wind_dir;
    private Double pressure_mm;
    private Double pressure_pa;
    private Double humidity;
    private Double prec_mm;
    private Double prec_period;
    private Double prec_prob;

}

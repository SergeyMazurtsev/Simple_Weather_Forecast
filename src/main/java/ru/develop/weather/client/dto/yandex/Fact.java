package ru.develop.weather.client.dto.yandex;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fact {
    private Double temp;
    private Double feels_like;
    private Double temp_water = 0d;
    private String icon;
    private String condition;
    private Double wind_speed;
    private Double wind_gust;
    private String wind_dir;
    private Double pressure_mm;
    private Double pressure_pa;
    private Double humidity;
    private String daytime;
    private Boolean polar;
    private String season;
    private Integer obs_time;
    private Double accum_prec;

}

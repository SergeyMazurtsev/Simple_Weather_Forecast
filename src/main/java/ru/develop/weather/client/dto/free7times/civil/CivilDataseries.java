package ru.develop.weather.client.dto.free7times.civil;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CivilDataseries {
    private Integer timepoint;
    private Integer cloudcover;
    private Integer lifted_index;
    private String prec_type;
    private Integer prec_amount;
    private Integer temp2m;
    private String rh2m;
    private CivilWind wind10m;
    private CivilWeatherType weather;
}

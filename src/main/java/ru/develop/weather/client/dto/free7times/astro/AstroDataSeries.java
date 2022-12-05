package ru.develop.weather.client.dto.free7times.astro;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AstroDataSeries {
    private Integer timepoint;
    private Integer cloudcover;
    private Integer seeing;
    private Integer transparency;
    private Integer lifted_index;
    private Integer rh2m;
    private AstroWind wind10m;
    private Integer temp2m;
    private String prec_type;
}

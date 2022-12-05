package ru.develop.weather.client.dto.free7times.civillight;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CivilLightTemperature {
    private Integer max;
    private Integer min;

    public static final String MAX_DESCRIPTION = "Maximum temperature Celsius degree.";
    public static final String MIN_DESCRIPTION = "Minimum temperature Celsius degree.";
}

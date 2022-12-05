package ru.develop.weather.client.dto.free7times.civil;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CivilWind {
    private String direction;
    private Integer speed;
}

package ru.develop.weather.client.dto.yandex;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Info {
    private Double lat;
    private Double lon;
    private String url;
}

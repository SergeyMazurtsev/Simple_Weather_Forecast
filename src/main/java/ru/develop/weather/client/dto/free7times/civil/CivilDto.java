package ru.develop.weather.client.dto.free7times.civil;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.develop.weather.client.dto.WeatherDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CivilDto implements WeatherDto {
    private String product;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyyMMddHH")
    @JsonFormat(pattern = "yyyyMMddHH")
    private LocalDateTime init;
    private List<CivilDataseries> dataseries;
}

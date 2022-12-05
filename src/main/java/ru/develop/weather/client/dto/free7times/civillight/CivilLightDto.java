package ru.develop.weather.client.dto.free7times.civillight;

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
public class CivilLightDto implements WeatherDto {
    private String product;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyyMMddHH")
    @JsonFormat(pattern = "yyyyMMddHH")
    private LocalDateTime init;
    private List<CivilLightDataSeries> dataseries;

    public static final String PRODUCT_DESCRIPTION = "The civil light version provide 7-day forecast on a day-to-day basis.";
    public static final String INIT_DESCRIPTION = "Date of initiation request.";
    public static final String DATA_SERIES_DESCRIPTION = "Detailed forecast information.";
}

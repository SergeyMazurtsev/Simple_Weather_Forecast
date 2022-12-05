package ru.develop.weather.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import ru.develop.weather.TypeWeatherRequest;
import ru.develop.weather.client.dto.WeatherDto;
import ru.develop.weather.client.dto.free7times.astro.AstroDto;
import ru.develop.weather.client.dto.free7times.civil.CivilDto;
import ru.develop.weather.client.dto.free7times.civillight.CivilLightDto;
import ru.develop.weather.client.dto.yandex.YandexDto;

import static ru.develop.weather.StaticEnvorements.CHOOSE_CITY_COORDINATES;


@Service
@Slf4j
public class WeatherClient {
    private final String astroUrl = "http://localhost:9090/astro";
    private final String civilLightUrl = "http://localhost:9090/civil_light";
    private final String civilUrl = "http://localhost:9090/civil";
    private final String yandexUrl = "http://localhost:9090/yandex";
    private RestTemplate rest = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();
    private HttpEntity request;
    private ResponseEntity<String> response;

    public WeatherClient() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("City-lat", CHOOSE_CITY_COORDINATES.get(0).toString());
        headers.add("City-lon", CHOOSE_CITY_COORDINATES.get(1).toString());
        request = new HttpEntity<>(null, headers);
    }

    public ResponseEntity<WeatherDto> getWeatherData(TypeWeatherRequest weatherRequest) {
        try {
            log.info("Sending request to server.");
            response = rest.exchange(getRequestUrl(weatherRequest), HttpMethod.GET, request, String.class);
        } catch (HttpStatusCodeException e) {
            log.info("Bad request.");
            return new ResponseEntity<>(e.getStatusCode());
        }
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            mapper.registerModule(new JavaTimeModule());
            WeatherDto apiResponse = null;
            try {
                log.info("Serializing response.");
                switch (weatherRequest) {
                    case Civil -> apiResponse = mapper.readValue(response.getBody(), CivilDto.class);
                    case CivilLight -> apiResponse = mapper.readValue(response.getBody(), CivilLightDto.class);
                    case Astro -> apiResponse = mapper.readValue(response.getBody(), AstroDto.class);
                    case Yandex -> apiResponse = mapper.readValue(response.getBody(), YandexDto.class);
                }
            } catch (JsonProcessingException e) {
                log.info("Bad serializing.");
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
            log.info("Get data.");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        log.info("Request with error code.");
        return new ResponseEntity<>(null, response.getStatusCode());
    }

    private String getRequestUrl(TypeWeatherRequest request) {
        return switch (request) {
            case Civil -> civilUrl;
            case CivilLight -> civilLightUrl;
            case Astro -> astroUrl;
            case Yandex -> yandexUrl;
        };
    }
}
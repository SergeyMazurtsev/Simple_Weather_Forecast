package ru.develop.weather;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StaticEnvorements {
    public static TypeWeatherRequest TYPE_REQUEST;
    public static String API_KEY;
    public final static String DEGREE = "" + (char) 186;
    public static Map<String, List<Double>> CITY_COORDINATES = new TreeMap<>();
    public static String CITY_NAME;
    public static List<Double> CHOOSE_CITY_COORDINATES = new ArrayList<>();

    public static final Map<Integer, String> CLOUD_COVER = Map.of(
            1, "0%-6%",
            2, "6%-19%",
            3, "19%-31%",
            4, "31%-44%",
            5, "44%-56%",
            6, "56%-69%",
            7, "69%-81%",
            8, "81%-94%",
            9, "94%-100%"
    );

    public static final Map<Integer, String> PRECIPITATION_AMOUNT = Map.of(
            0, "None",
            1, "0-0.25mm/hr",
            2, "0.25-1mm/hr",
            3, "1-4mm/hr",
            4, "4-10mm/hr",
            5, "10-16mm/hr",
            6, "16-30mm/hr",
            7, "30-50mm/hr",
            8, "50-75mm/hr",
            9, "Over 75mm/hr"
    );

    public static final Map<String, String> WIND_DIRECTION = Map.of(
            "N", "North",
            "NE", "North-East",
            "E", "East",
            "SE", "South-East",
            "S", "South",
            "SW", "South-West",
            "W", "West",
            "NW", "North-West"
    );


    static {
        CITY_COORDINATES.put("Москва", List.of(55.75, 37.62));
        CITY_COORDINATES.put("Астрахань", List.of(46.35, 48.04));
        CITY_COORDINATES.put("Барнаул", List.of(53.36, 83.76));
        CITY_COORDINATES.put("Брянск", List.of(53.25, 34.37));
        CITY_COORDINATES.put("Владивосток", List.of(43.11, 131.87));
        CITY_COORDINATES.put("Волгоград", List.of(48.72, 44.5));
        CITY_COORDINATES.put("Воронеж", List.of(51.67, 39.18));
        CITY_COORDINATES.put("Екатеринбург", List.of(56.85, 60.61));
        CITY_COORDINATES.put("Иваново", List.of(57d, 40.97));
        CITY_COORDINATES.put("Ижевск", List.of(56.85, 53.2));
        CITY_COORDINATES.put("Иркутск", List.of(52.3, 104.3));
        CITY_COORDINATES.put("Казань", List.of(55.79, 49.12));
        CITY_COORDINATES.put("Калининград", List.of(54.71, 20.51));
        CITY_COORDINATES.put("Кемерово", List.of(55.33, 86.08));
        CITY_COORDINATES.put("Киров", List.of(58.6, 49.66));
        CITY_COORDINATES.put("Краснодар", List.of(45.04, 38.98));
        CITY_COORDINATES.put("Красноярск", List.of(56.02, 92.87));
        CITY_COORDINATES.put("Липецк", List.of(52.6, 39.57));
        CITY_COORDINATES.put("Махачкала", List.of(42.98, 47.5));
        CITY_COORDINATES.put("Набережные Челны", List.of(55.73, 52.41));
        CITY_COORDINATES.put("Нижний Новгород", List.of(56.33, 44d));
        CITY_COORDINATES.put("Новокузнецк", List.of(53.76, 87.11));
        CITY_COORDINATES.put("Новосибирск", List.of(55.04, 82.93));
        CITY_COORDINATES.put("Омск", List.of(54.99, 73.37));
        CITY_COORDINATES.put("Оренбург", List.of(51.77, 55.1));
        CITY_COORDINATES.put("Пенза", List.of(53.2, 45d));
        CITY_COORDINATES.put("Пермь", List.of(58.01, 56.25));
        CITY_COORDINATES.put("Ростов-на-Дону", List.of(47.23, 39.72));
        CITY_COORDINATES.put("Рязань", List.of(54.63, 39.69));
        CITY_COORDINATES.put("Самара", List.of(53.2, 50.15));
        CITY_COORDINATES.put("Санкт-Петербург", List.of(59.94, 30.31));
        CITY_COORDINATES.put("Саратов", List.of(51.54, 46.01));
        CITY_COORDINATES.put("Тольятти", List.of(53.53, 49.35));
        CITY_COORDINATES.put("Томск", List.of(56.5, 84.97));
        CITY_COORDINATES.put("Тула", List.of(54.2, 37.62));
        CITY_COORDINATES.put("Тюмень", List.of(57.15, 65.53));
        CITY_COORDINATES.put("Ульяновск", List.of(54.33, 48.39));
        CITY_COORDINATES.put("Уфа", List.of(54.74, 55.97));
        CITY_COORDINATES.put("Хабаровск", List.of(48.48, 135.08));
        CITY_COORDINATES.put("Чебоксары", List.of(56.13, 47.25));
        CITY_COORDINATES.put("Челябинск", List.of(55.15, 61.43));
        CITY_COORDINATES.put("Ярославль", List.of(57.63, 39.87));
    }
}

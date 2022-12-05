module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.boot.autoconfigure;
    requires spring.boot.starter;
    requires spring.boot.actuator;
    requires spring.boot;
    requires spring.context;
    requires lombok;
    requires spring.boot.starter.json;
    requires spring.boot.starter.logging;
    requires spring.web;
    requires org.slf4j;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    exports ru.develop.weather;
    opens ru.develop.weather to javafx.fxml;
    exports ru.develop.weather.client;
    opens ru.develop.weather.client to javafx.fxml, com.fasterxml.jackson.databind;

    exports ru.develop.weather.client.dto.free7times.civillight;
    opens ru.develop.weather.client.dto.free7times.civillight to javafx.fxml, com.fasterxml.jackson.databind;//com.google.gson;
    exports ru.develop.weather.client.dto.free7times.civil;
    opens ru.develop.weather.client.dto.free7times.civil to javafx.fxml, com.fasterxml.jackson.databind;
}
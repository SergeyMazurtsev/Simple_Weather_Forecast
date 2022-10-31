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
    requires com.google.gson;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    exports ru.develop.weather;
    opens ru.develop.weather to javafx.fxml;
    exports ru.develop.weather.scene;
    opens ru.develop.weather.scene to javafx.fxml, com.google.gson;
}
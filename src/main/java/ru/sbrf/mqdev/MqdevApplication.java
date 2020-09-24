package ru.sbrf.mqdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class MqdevApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqdevApplication.class, args);
    }

}
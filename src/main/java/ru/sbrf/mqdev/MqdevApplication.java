package ru.sbrf.mqdev;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@Slf4j
@SpringBootApplication
public class MqdevApplication {

    @Value("${server.port}")
    private String serverPort;

    public static void main(String[] args) {
        SpringApplication.run(MqdevApplication.class, args);
    }

    @Bean
    public ApplicationRunner welcomeMessage() {
        return args -> {
            log.info("Hello! Open URL in browser {}", "http://localhost:".concat(serverPort));
        };
    }

}
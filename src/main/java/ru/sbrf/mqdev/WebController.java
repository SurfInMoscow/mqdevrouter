package ru.sbrf.mqdev;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class WebController {

    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    public void process(@RequestBody String payload) {
        System.out.println(payload);
    }

}

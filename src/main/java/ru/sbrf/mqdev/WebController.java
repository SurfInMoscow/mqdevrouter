package ru.sbrf.mqdev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping(value = "/api")
public class WebController {

    private final ExecutorService executor = Executors.newCachedThreadPool();

    private final MessageChannel input;

    @Value("${ibm.mq.queue}")
    private String destinationQueue;

    @Autowired
    public WebController(MessageChannel input) {
        this.input = input;
    }

    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> process(@RequestBody String payload) {
        executor.execute(() -> {
            input.send(MessageBuilder.withPayload(payload).build());
        });

        return ResponseEntity.ok().header("Location", "Accepted to sent " .concat(destinationQueue)).build();
    }

}

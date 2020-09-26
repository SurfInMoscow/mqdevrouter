package ru.voroby.mqdev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Configuration
@EnableIntegration
public class PayloadIntegration {

    @Bean
    public MessageChannel input() {
        return new DirectChannel();
    }

    @Bean
    public Processing processing() {
        return new Processing();
    }

    @Bean
    public IntegrationFlow processPayload() {
        return IntegrationFlows
                .from(input())
                .handle("processing", "process")
                .get();
    }

    @Component
    class Processing {

        @Autowired
        private JmsController jmsController;

        @Value("${ibm.mq.queue}")
        private String destinationQueue;

        public void process(String payload) throws JMSException {
            jmsController.sendPayload(destinationQueue, payload);
        }
    }
}

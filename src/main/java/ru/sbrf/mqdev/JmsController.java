package ru.sbrf.mqdev;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.Objects;

@Component
@Slf4j
public class JmsController {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public JmsController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendPayload(String destination, String payload) throws JMSException {
        TextMessage message = createTextMessage(Objects.requireNonNull(jmsTemplate.getConnectionFactory()));
        message.setText(payload);
        jmsTemplate.convertAndSend(destination, message);
        log.info("Message {} is sent to {}", message.getJMSMessageID(), destination);
    }

    /*@JmsListener(destination = "${ibm.mq.queue}")
    public void receivePayload(TextMessage message) {
        System.out.println(message);
    }*/

    private TextMessage createTextMessage(ConnectionFactory connectionFactory) {
        return connectionFactory.createContext().createTextMessage();
    }

}

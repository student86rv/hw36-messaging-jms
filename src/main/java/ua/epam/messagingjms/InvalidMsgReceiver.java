package ua.epam.messagingjms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static ua.epam.messagingjms.ActiveMQConfig.INVALID_RESPONSE_CHANNEL;

@Component
public class InvalidMsgReceiver {

    private static Logger logger = LoggerFactory.getLogger(InvalidMsgReceiver.class);

    @JmsListener(destination = INVALID_RESPONSE_CHANNEL)
    public void receiveMessage(@Payload ResponseMsg responseMsg) {
        logger.info("Invalid Message receiver {} - Invalid response received: {}", this, responseMsg);
    }
}

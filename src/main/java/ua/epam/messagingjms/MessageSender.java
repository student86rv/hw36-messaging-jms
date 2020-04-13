package ua.epam.messagingjms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static ua.epam.messagingjms.ActiveMQConfig.*;

@Service
public class MessageSender {

    private static Logger logger = LoggerFactory.getLogger(MessageSender.class);

    private Map<UUID, RequestMsg> sentMessages = new HashMap<>();

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendRequest() {
        for (int i = 0; i < MESSAGES_QTY; i++) {
            RequestMsg requestMsg = RequestFactory.getRandomRequestMsg();
            logger.info("Message sender: {} Sending Request: {}", this, requestMsg);
            sentMessages.put(requestMsg.getId(), requestMsg);
            jmsTemplate.convertAndSend(REQUEST_CHANNEL, requestMsg);
        }
    }

    @JmsListener(destination = RESPONSE_CHANNEL)
    public void receiveResponse(@Payload ResponseMsg responseMsg) {
        UUID responseId = responseMsg.getId();
        if (sentMessages.containsKey(responseId)) {
            RequestMsg sentMsg = sentMessages.get(responseId);
            int checkSum = sentMsg.getFirstNumber() + sentMsg.getSecondNumber();
            if (responseMsg.getSumm() == checkSum) {
                logger.info("Message sender: {} Received correct response: {}", this, responseMsg);
            } else {
                logger.info("Message sender: {} Received invalid checksum response: {}", this, responseMsg);
                sendToInvalid(responseMsg);
            }
        } else {
            logger.info("Message sender: {} Received response with invalid ID: {}", this, responseMsg);
            sendToInvalid(responseMsg);
        }
    }

    private void sendToInvalid(ResponseMsg responseMsg) {
        logger.info("Message sender: {} Sending to Invalid Channel: {}", this, responseMsg);
        jmsTemplate.convertAndSend(INVALID_RESPONSE_CHANNEL, responseMsg);
    }
}

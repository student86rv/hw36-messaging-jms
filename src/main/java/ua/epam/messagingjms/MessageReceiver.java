package ua.epam.messagingjms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static ua.epam.messagingjms.ActiveMQConfig.*;

@Component
public class MessageReceiver {

    private static Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    private Map<UUID, RequestMsg> requests = new HashMap<>();

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = REQUEST_CHANNEL)
    public void receiveRequest(@Payload RequestMsg request) {
        logger.info("Message receiver {} - Request received: {}", this, request);
        requests.put(request.getId(), request);
        if (requests.size() == MESSAGES_QTY) {
            sendResponses();
            sendWrongIdResponse();
        }
    }

    private void sendResponses() {
        for (Map.Entry<UUID, RequestMsg> entry : requests.entrySet()) {
            RequestMsg requestMsg = entry.getValue();
            int sum = requestMsg.getFirstNumber() + requestMsg.getSecondNumber();
            ResponseMsg responseMsg = new ResponseMsg(requestMsg.getId(), sum);
            logger.info("Message receiver {} - Sending response: {}", this, responseMsg);
            jmsTemplate.convertAndSend(RESPONSE_CHANNEL, responseMsg);
        }
    }

    private void sendWrongIdResponse() {
        ResponseMsg responseMsg = new ResponseMsg(UUID.randomUUID(), 0);
        logger.info("Message receiver {} - Sending wrong-ID response: {}", this, responseMsg);
        jmsTemplate.convertAndSend(RESPONSE_CHANNEL, responseMsg);
    }
}

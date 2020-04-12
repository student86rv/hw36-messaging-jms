package ua.epam.messagingjms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@EnableJms
@Configuration
public class ActiveMQConfig {

    public static final String REQUEST_CHANNEL = "request_channel";
    public static final String RESPONSE_CHANNEL = "response_channel";
    public static final String INVALID_RESPONSE_CHANNEL = "invalid_response_channel";

    public static final int MESSAGES_QTY = 10;

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        converter.setObjectMapper(new ObjectMapper());
        return converter;
    }
}

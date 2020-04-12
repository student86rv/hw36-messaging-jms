package ua.epam.messagingjms;

import java.util.Random;
import java.util.UUID;

public class RequestFactory {

    private static final Random RANDOM = new Random();

    public static RequestMsg getRandomRequestMsg() {
        RequestMsg requestMsg = new RequestMsg();
        requestMsg.setId(UUID.randomUUID());
        requestMsg.setFirstNumber(RANDOM.nextInt(100));
        requestMsg.setSecondNumber(RANDOM.nextInt(100));
        return requestMsg;
    }
}

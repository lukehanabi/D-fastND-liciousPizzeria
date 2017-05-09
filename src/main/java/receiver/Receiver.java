package receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Order;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by lucasluduena on 07/05/17.
 */
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);
    private ObjectMapper mapper = new ObjectMapper();

    public void receiveMessage(String message) throws IOException {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public Order convertToOrder(String order) throws IOException {
        return mapper.readValue(order, Order.class);
    }

}

package pizzeria;

import domain.Order;
import domain.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Queueing Testing.
 * Created by lucasluduena on 07/05/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Test
    public void testOrderEntryPickupQueue() throws Exception {
        rabbitTemplate.convertAndSend(Application.entryQueueName,
                (new Order("1", "DONE", true)).toString());
    }

    @Test
    public void testOrderEntryDeliveryQueue() throws Exception {
        rabbitTemplate.convertAndSend(Application.entryQueueName,
                (new Order("2", "DONE", false)).toString());
    }

    @Test
    public void testOrderEntryNotDoneQueue() throws Exception {
        rabbitTemplate.convertAndSend(Application.entryQueueName,
                (new Order("3", Status.ASSIGNED.toString(), true)).toString());
    }

    @Test
    public void testOrderEntrySinkQueue() throws Exception {
        rabbitTemplate.convertAndSend(Application.driverQueueName,
                (new Order("4", Status.DELIVERED.toString(), false)).toString());
    }
}

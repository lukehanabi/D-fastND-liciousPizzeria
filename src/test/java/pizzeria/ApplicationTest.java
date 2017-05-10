package pizzeria;

import domain.Order;
import domain.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import receiver.*;

import java.util.concurrent.TimeUnit;

/**
 * Queueing Testing.
 * Created by lucasluduena on 07/05/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private EntryOrderReceiver entryOrderReceiver;

    @Autowired
    private StoreOrderReceiver storeOrderReceiver;

    @Autowired
    private PreparingEntryReceiver preparingEntryReceiver;

    @Autowired
    private PickupEntryReceiver pickupEntryReceiver;

    @Autowired
    private DriverEntryReceiver driverEntryReceiver;

    @Test
    public void testOrderEntryPickupQueue() throws Exception {
        rabbitTemplate.convertAndSend(Application.entryQueueName,
                (new Order("1", "DONE", true)).toString());
        entryOrderReceiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void testOrderEntryDeliveryQueue() throws Exception {
        rabbitTemplate.convertAndSend(Application.entryQueueName,
                (new Order("2", "DONE", false)).toString());
        entryOrderReceiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void testOrderEntryNotDoneQueue() throws Exception {
        rabbitTemplate.convertAndSend(Application.entryQueueName,
                (new Order("3", Status.ASSIGNED.toString(), true)).toString());
        entryOrderReceiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void testOrderEntrySinkQueue() throws Exception {
        rabbitTemplate.convertAndSend(Application.driverQueueName,
                (new Order("4", Status.DELIVERED.toString(), false)).toString());
        driverEntryReceiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
}

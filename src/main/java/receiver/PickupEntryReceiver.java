package receiver;

import domain.Order;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pizzeria.Application;
import service.LocationService;
import service.NotificationService;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

/**
 * Pickup Order Queue Receiver.
 * Created by lucasluduena on 07/05/17.
 */
@Component
public class PickupEntryReceiver extends Receiver {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    LocationService locationService;

    @Autowired
    NotificationService notificationService;

    @Override
    @RabbitListener(queues = "pickup-entryQueue")
    public void receiveMessage(String order) throws IOException {
        LOGGER.info(order);

        Order theOrder = convertToOrder(order);

        if (theOrder.getStatus().equals("PICKEDUP")) {
            /** Order Delivered*/
            rabbitTemplate.convertAndSend(Application.getSinkQueueName(), order);
        }
        notificationService.sendSMSandEmail(order.toString());
    }


}
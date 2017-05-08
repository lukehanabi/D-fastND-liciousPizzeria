package receiver;

import domain.Order;
import domain.Status;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pizzeria.Application;
import service.NotificationService;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

/**
 * Created by lucasluduena on 07/05/17.
 */
@Component
public class PreparingEntryReceiver extends Receiver {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    NotificationService notificationService;

    @Override
    @RabbitListener(queues = "preparing-entryQueue")
    public void receiveMessage(String order) throws IOException {
        LOGGER.info(order);

        Order theOrder = convertToOrder(order);

            if (theOrder.getStatus().equals(Status.DONE.toString()) &&
                    theOrder.getPickup().equals(true)) {
                rabbitTemplate.convertAndSend(Application.getPickupQueueName(), order);
            } else if (theOrder.getStatus().equals(Status.DONE.toString()) &&
                    theOrder.getPickup().equals(false)) {
                rabbitTemplate.convertAndSend(Application.getDriverQueueName(), order);
            }

        notificationService.sendSMSandEmail(order.toString());
    }


}

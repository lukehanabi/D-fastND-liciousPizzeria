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
 * Created by lucasluduena on 07/05/17.
 */
@Component
public class DriverEntryReceiver extends Receiver {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    LocationService locationService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    NotificationService notificationService;

    @Override
    @RabbitListener(queues = "driver-entryQueue")
    public void receiveMessage(String order) throws IOException {
        LOGGER.info(order.toString());

        Order theOrder = convertToOrder(order);

        if (theOrder.getStatus().equals("DELIVERED")) {
            /** Order Delivered*/
            rabbitTemplate.convertAndSend(Application.getSinkQueueName(), order);
        } else {
            /** Location Needed*/
            theOrder.setLocation(locationService.getOrderlocation((theOrder)));
            //rabbitTemplate.convertAndSend(Application.getDriverQueueName(), order);
        }
        notificationService.sendSMSandEmail(order.toString());
    }

}
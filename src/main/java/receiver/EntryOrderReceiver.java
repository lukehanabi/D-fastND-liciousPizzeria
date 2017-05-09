package receiver;

import domain.Order;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pizzeria.Application;
import service.NotificationService;
import service.StoreAssignerService;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

/**
 * Created by lucasluduena on 07/05/17.
 */
@Component
public class EntryOrderReceiver extends Receiver {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    StoreAssignerService storeAssignerService;

    @Autowired
    NotificationService notificationService;

    @RabbitListener(queues = "entry-entryQueue")
    public void receiveMessage(String order) throws IOException {
        LOGGER.info(order.toString());

        Order theOrder = convertToOrder(order);
        theOrder.setStore(storeAssignerService.getStore(theOrder.getDestination()));
        rabbitTemplate.convertAndSend(Application.getStoreQueueName(), order);
        notificationService.sendSMSandEmail(order.toString());
    }

}

package receiver;

import domain.Order;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pizzeria.Application;
import service.NotificationService;
import service.StaffAssignerService;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

/**
 * Store selection Queue Receiver.
 * Created by lucasluduena on 07/05/17.
 */
@Component
public class StoreOrderReceiver extends Receiver {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    NotificationService notificationService;

    @Autowired
    StaffAssignerService staffAssignerService;

    @Override
    @RabbitListener(queues = "store-entryQueue")
    public void receiveMessage(String order) throws IOException {
        LOGGER.info(order);

        Order theOrder = convertToOrder(order);
        theOrder.setStaff(staffAssignerService.getStaffMember(theOrder.getStore()));
        rabbitTemplate.convertAndSend(Application.getPreparingQueueName(), order);
        notificationService.sendSMSandEmail(order.toString());
    }

}
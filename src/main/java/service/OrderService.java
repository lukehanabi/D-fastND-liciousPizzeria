package service;

import domain.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizzeria.Application;

/**
 * Created by lucasluduena on 07/05/17.
 */
@Service
public class OrderService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    StoreAssignerService storeAssignerService;

    @Autowired
    LocationService locationService;

    public String receiveOrder(Order order) {
        rabbitTemplate.convertAndSend(Application.getEntryQueueName(), order);
        return order.toString();
    }
}

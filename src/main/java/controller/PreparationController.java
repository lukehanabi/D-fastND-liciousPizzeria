package controller;

import domain.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pizzeria.Application;
import service.NotificationService;
import service.PreparationStatusService;

/**
 * Sets the Preparation Status and returns it for the App.
 * <p>
 * Created by lucasluduena on 07/05/17.
 */
@RestController
@RequestMapping("/preparation")
public class PreparationController {

    @Autowired
    PreparationStatusService preparationStatusService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    NotificationService notificationService;

    @RequestMapping(value = "/status/{status}", method = {RequestMethod.POST})
    public Order changeStatus(@RequestBody Order order, @PathVariable String status) {
        System.out.println(order.toString());

        order.setStatus(status.toString());

        rabbitTemplate.convertAndSend(Application.getPreparingQueueName(), order);

        notificationService.sendSMSandEmail(order.toString());

        return order;
    }


}

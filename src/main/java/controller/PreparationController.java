package controller;

import domain.Order;
import domain.Status;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pizzeria.Application;
import service.NotificationService;
import service.PreparationStatusService;

/**
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

    @RequestMapping(value = "/status/{status}", method = { RequestMethod.POST })
    public void changeStatus(@PathVariable Order order, @PathVariable String status) {
        System.out.println(order.toString());

        order.setStatus(status.toString());

        rabbitTemplate.convertAndSend(Application.getPreparingQueueName(), order);

        notificationService.sendSMSandEmail(order.toString());
    }


}

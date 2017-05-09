package controller;

import domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.NotificationService;

/**
 * Notifies order status by sms and email.
 * Created by lucasluduena on 07/05/17.
 */
@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @RequestMapping("/sms")
    @ResponseBody
    public String sendSMS(@RequestBody Order order) {
        return notificationService.sendSMS(order.toString());
    }

    @RequestMapping("/email")
    @ResponseBody
    private String sendEmail(@RequestBody Order order) {
        return notificationService.sendEmail(order.toString());
    }

    @RequestMapping("/smsandemail")
    @ResponseBody
    private String sendSMSandEmail(@RequestBody Order order) {
        return notificationService.sendSMSandEmail(order.toString());
    }
}

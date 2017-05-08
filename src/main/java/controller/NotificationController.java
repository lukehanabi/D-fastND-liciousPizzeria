package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.NotificationService;

/**
 * Created by lucasluduena on 07/05/17.
 */
@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @RequestMapping("/sms")
    @ResponseBody
    public String sendSMS(String data) {
        return notificationService.sendSMS(data);
    }

    @RequestMapping("/email")
    @ResponseBody
    private String sendEmail(String data) {
        return notificationService.sendEmail(data);
    }

    @RequestMapping("/smsandemail")
    @ResponseBody
    private String sendSMSandEmail(String data) {
        return notificationService.sendSMSandEmail(data);
    }
}

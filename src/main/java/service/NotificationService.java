package service;

import org.springframework.stereotype.Service;

/**
 * Created by lucasluduena on 07/05/17.
 */
@Service
public class NotificationService {

    public String sendSMS(String data) {
        return "SMS OK";
    }

    public String sendEmail(String data) {
        return "EMAIL OK";
    }

    public String sendSMSandEmail(String data) {
        sendSMS("");
        sendEmail("");
        return "OK";
    }
}

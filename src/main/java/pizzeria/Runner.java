package pizzeria;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import receiver.Receiver;

/**
 * Created by lucasluduena on 07/05/17.
 */
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;
    private final ConfigurableApplicationContext context;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate,
            ConfigurableApplicationContext context) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(Application.entryQueueName, "Hello from RabbitMQ!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);

        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(Application.storeQueueName, "Hello from RabbitMQ!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);

        context.close();
    }

}

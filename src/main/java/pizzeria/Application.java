package pizzeria;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import receiver.*;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This Application manages changes and aggregations
 * to a pizza order between RabbitMQ Queues.
 * Interactions with external apps are managed by controllers
 * and services.
 */
@SpringBootApplication
@ComponentScan(basePackages = "receiver")
@ComponentScan(basePackages = "service")
@ComponentScan(basePackages = "controller")
@EnableSwagger2
public class Application {

    final static String entryQueueName = "entry-entryQueue";

    final static String storeQueueName = "store-entryQueue";

    final static String preparingQueueName = "preparing-entryQueue";

    final static String driverQueueName = "driver-entryQueue";

    final static String pickupQueueName = "pickup-entryQueue";

    final static String sinkQueueName = "sink-entryQueue";

    @Bean
    Queue entryQueue() {
        return new Queue(entryQueueName, false);
    }


    @Bean
    Queue storeQueue() {
        return new Queue(storeQueueName, false);
    }

    @Bean
    Queue preparingQueue() {
        return new Queue(preparingQueueName, false);
    }

    @Bean
    Queue driverQueue() {
        return new Queue(driverQueueName, false);
    }

    @Bean
    Queue pickupQueue() {
        return new Queue(pickupQueueName, false);
    }

    @Bean
    Queue sinkQueue() {
        return new Queue(sinkQueueName, true);
    }


    @Bean
    TopicExchange exchange() {
        return new TopicExchange("entry-queue-exchange");
    }

    @Bean
    Binding bindEntryQueue(Queue entryQueue, TopicExchange exchange) {
        return BindingBuilder.bind(entryQueue).to(exchange).with(entryQueueName);
    }

    @Bean
    Binding bindStoreQueue(Queue storeQueue, TopicExchange exchange) {
        return BindingBuilder.bind(storeQueue).to(exchange).with(storeQueueName);
    }

    @Bean
    Binding bindPreparingQueue(Queue preparingQueue, TopicExchange exchange) {
        return BindingBuilder.bind(preparingQueue).to(exchange).with(preparingQueueName);
    }

    @Bean
    Binding bindDriverQueue(Queue driverQueue, TopicExchange exchange) {
        return BindingBuilder.bind(driverQueue).to(exchange).with(driverQueueName);
    }

    @Bean
    Binding bindPickupQueue(Queue pickupQueue, TopicExchange exchange) {
        return BindingBuilder.bind(pickupQueue).to(exchange).with(pickupQueueName);
    }

    @Bean
    Binding bindSinkQueue(Queue sinkQueue, TopicExchange exchange) {
        return BindingBuilder.bind(sinkQueue).to(exchange).with(sinkQueueName);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(entryQueueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    SimpleMessageListenerContainer containerStore(ConnectionFactory connectionFactory,
                                                  MessageListenerAdapter listenerAdapterStore) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(storeQueueName);
        container.setMessageListener(listenerAdapterStore);
        return container;
    }

    @Bean
    SimpleMessageListenerContainer containerPrep(ConnectionFactory connectionFactory,
                                                 MessageListenerAdapter listenerAdapterPrep) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(preparingQueueName);
        container.setMessageListener(listenerAdapterPrep);
        return container;
    }

    @Bean
    SimpleMessageListenerContainer containerDriver(ConnectionFactory connectionFactory,
                                                   MessageListenerAdapter listenerAdapterDriver) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(driverQueueName);
        container.setMessageListener(listenerAdapterDriver);
        return container;
    }

    @Bean
    SimpleMessageListenerContainer containerPickup(ConnectionFactory connectionFactory,
                                                   MessageListenerAdapter listenerAdapterPickup) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(pickupQueueName);
        container.setMessageListener(listenerAdapterPickup);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(EntryOrderReceiver foo) {
        return new MessageListenerAdapter(foo, "receiveMessage");
    }

    @Bean
    MessageListenerAdapter listenerAdapterStore(StoreOrderReceiver foo) {
        return new MessageListenerAdapter(foo, "receiveMessage");
    }

    @Bean
    MessageListenerAdapter listenerAdapterPrep(PreparingEntryReceiver foo) {
        return new MessageListenerAdapter(foo, "receiveMessage");
    }

    @Bean
    MessageListenerAdapter listenerAdapterDriver(DriverEntryReceiver foo) {
        return new MessageListenerAdapter(foo, "receiveMessage");
    }

    @Bean
    MessageListenerAdapter listenerAdapterPickup(PickupEntryReceiver foo) {
        return new MessageListenerAdapter(foo, "receiveMessage");
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
    }

    public static String getEntryQueueName() {
        return entryQueueName;
    }

    public static String getStoreQueueName() {
        return storeQueueName;
    }

    public static String getPreparingQueueName() {
        return preparingQueueName;
    }

    public static String getDriverQueueName() {
        return driverQueueName;
    }

    public static String getPickupQueueName() {
        return pickupQueueName;
    }

    public static String getSinkQueueName() {
        return sinkQueueName;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

}

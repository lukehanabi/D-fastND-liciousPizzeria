# D-fastND-liciousPizzeria

Based on a RabbitMQ architecture:

    final static String entryQueueName = "entry-entryQueue";

    final static String storeQueueName = "store-entryQueue";

    final static String preparingQueueName = "preparing-entryQueue";

    final static String driverQueueName = "driver-entryQueue";

    final static String pickupQueueName = "pickup-entryQueue";

    final static String sinkQueueName = "sink-entryQueue";
    
# The initial Order is entered in the first queue and aggregations are performed over new order messages.

# It can be built with gradle or maven:

./gradlew build

mvn clean install

mvn spring-boot:run

# Dockerfile build:

sudo docker build .../D-fastND-liciousPizzeria/src/main/java/docker

# Sequence Diagram:

Pizzeria.dia

https://github.com/lukehanabi/D-fastND-liciousPizzeria/blob/master/Pizzeria.png


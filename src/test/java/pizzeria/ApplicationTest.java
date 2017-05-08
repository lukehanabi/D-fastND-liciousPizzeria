/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pizzeria;

import domain.Order;
import domain.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import receiver.EntryOrderReceiver;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @MockBean
    private Runner runner;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //@Autowired
    private EntryOrderReceiver receiver;


    @Test
    public void testOrderEntryPickupQueue() throws Exception {
        rabbitTemplate.convertAndSend(Application.entryQueueName,
                (new Order("1", "DONE", true)).toString());
    }

    @Test
    public void testOrderEntryDeliveryQueue() throws Exception {
        rabbitTemplate.convertAndSend(Application.entryQueueName,
                (new Order("2", "DONE", false)).toString());
    }

    @Test
    public void testOrderEntryNotDoneQueue() throws Exception {
        rabbitTemplate.convertAndSend(Application.entryQueueName,
                (new Order("3", Status.ASSIGNED.toString(), true)).toString());
    }

    @Test
    public void testOrderEntrySinkQueue() throws Exception {
        rabbitTemplate.convertAndSend(Application.driverQueueName,
                (new Order("4", Status.DELIVERED.toString(), false)).toString());
    }
}

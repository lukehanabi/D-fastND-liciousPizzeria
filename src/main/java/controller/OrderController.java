package controller;

import domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.OrderService;

/**
 * Interacts with the Mobile and Web apps to create,
 * modify, or cancel an order.
 * Created by lucasluduena on 07/05/17.
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/order", method = {RequestMethod.POST})
    @ResponseBody
    private String receiveOrder(@RequestBody Order order) {
        return orderService.receiveOrder(order);
    }

}

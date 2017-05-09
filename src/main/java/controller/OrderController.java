package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.OrderService;

/**
 * Created by lucasluduena on 07/05/17.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "", method = {RequestMethod.POST})
    @ResponseBody
    private String receiveOrder(@RequestParam(value = "order") String order) {
        ObjectMapper objectMapper = new ObjectMapper();
        Order theOrder = objectMapper.convertValue(order, Order.class);
        return orderService.receiveOrder(theOrder);
    }

}

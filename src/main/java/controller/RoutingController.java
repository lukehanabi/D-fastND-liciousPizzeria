package controller;

import domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.RoutingService;

/**
 * Gets Routing maps for an Order or a List of Orders.
 * <p>
 * Created by lucasluduena on 07/05/17.
 */
@RestController
public class RoutingController {

    @Autowired
    RoutingService routingService;

    @RequestMapping(value = "/routing", method = {RequestMethod.POST})
    @ResponseBody
    private String getRouting(@RequestBody Order order) {
        return routingService.getRouting(order.getStore(), order.getDestination(),
                order.getDriver());
    }

}

package controller;

import domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.LocationService;

/**
 * Created by lucasluduena on 07/05/17.
 */
@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    LocationService locationService;

    @RequestMapping("")
    @ResponseBody
    private String getLocation(Order order) {
        return locationService.getOrderlocation(order);
    }

}

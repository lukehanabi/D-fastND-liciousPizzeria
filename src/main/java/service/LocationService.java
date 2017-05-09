package service;

import domain.Order;
import org.springframework.stereotype.Service;

/**
 * Gets Order Location.
 * Created by lucasluduena on 07/05/17.
 */
@Service
public class LocationService {

    public String getOrderlocation(Order orderId) {
        return "(-41.288889, 174.777222)";
    }

    public Order setOrderLocation(Order order) {
        order.setLocation("(-41.288889, 174.777222)");
        return order;
    }
}

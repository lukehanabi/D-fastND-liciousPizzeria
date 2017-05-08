package service;

import domain.Driver;
import domain.Order;
import org.springframework.stereotype.Service;

/**
 * Created by lucasluduena on 07/05/17.
 */
@Service
public class DriverAssignerService {

    public Driver getDriver(Order order) {
        return new Driver("Lucas L");
    }

    public String getDriverLocation(Driver driver) {
        return "(-41.288889, 174.777222)";
    }
}

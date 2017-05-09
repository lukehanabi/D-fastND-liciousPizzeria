package service;

import domain.Destination;
import domain.Driver;
import domain.Store;
import org.springframework.stereotype.Service;

/**
 * Gets Routing details for the driver.
 * Created by lucasluduena on 07/05/17.
 */
@Service
public class RoutingService {

    public String getRouting(Store store, Destination destination, Driver driver) {
        return "Routing URL";
    }

}

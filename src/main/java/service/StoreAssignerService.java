package service;

import domain.Destination;
import domain.Store;
import org.springframework.stereotype.Service;

/**
 * Sets nearest Store for the pizza order Destination.
 * Created by lucasluduena on 07/05/17.
 */
@Service
public class StoreAssignerService {

    public Store getStore(Destination destination) {
        return new Store();
    }
}

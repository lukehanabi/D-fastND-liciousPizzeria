package service;

import domain.Destination;
import domain.Store;
import org.springframework.stereotype.Service;

/**
 * Created by lucasluduena on 07/05/17.
 */
@Service
public class StoreAssignerService {

    public Store getStore(Destination destination) {
        return new Store();
    }
}

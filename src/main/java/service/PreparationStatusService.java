package service;

import domain.Status;
import org.springframework.stereotype.Service;

/**
 * Manages pizza preparation status.
 * Created by lucasluduena on 07/05/17.
 */
@Service
public class PreparationStatusService {

    private String getStatus() {
        return Status.ASSIGNED.toString();
    }

    private String setStatus(String status) {
        return status + " changed.";
    }
}

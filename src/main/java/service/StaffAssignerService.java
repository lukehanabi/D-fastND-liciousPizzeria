package service;

import domain.Store;
import org.springframework.stereotype.Service;

/**
 * Sets a Staff Memeber to get pizza done.
 * Created by lucasluduena on 07/05/17.
 */
@Service
public class StaffAssignerService {

    public String getStaffMember(Store store) {
        return "Lucas Staff Member";
    }
}

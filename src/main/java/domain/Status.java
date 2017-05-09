package domain;

/**
 * Created by lucasluduena on 07/05/17.
 */
public enum Status {
    ASSIGNED("ASSIGNED"),
    CRUST("CRUST"),
    SAUCE("SAUCE"),
    OVEN("OVEN"),
    DONE("DONE"),
    DELIVERED("DELIVERED"),
    PICKEDUP("PICKEDUP");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

package domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by lucasluduena on 07/05/17.
 */
public class Order {
    /**
     * Order ID
     */
    @JsonProperty("id")
    String id;
    /**
     * Client Data
     */
    Client client;
    /**
     * Order Status
     */
    @JsonProperty("status")
    String status;
    /**
     * Destination Address
     */
    Destination destination;
    /**
     * items ordered
     */
    List<Items> items;
    /**
     * Order taken by member
     */
    String staff;
    /**
     * GPS Location
     */
    String location;
    /**
     * Store Data
     */
    Store store;
    /**
     * Driver assigned
     */
    Driver driver;
    /**
     * Order for pickup: true or false
     */
    @JsonProperty("pickup")
    Boolean pickup;

    public Order(String orderId, String status, Boolean pickup) {
        this.id = orderId;
        this.status = status;
        this.pickup = pickup;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Boolean getPickup() {
        return pickup;
    }

    public void setPickup(Boolean pickup) {
        this.pickup = pickup;
    }

    @Override
    public String toString() {
        return "{ \"id\" : \"" + this.getId() + "\"," +
                "\"status\" : \"" + this.getStatus() + "\"," +
                "\"pickup\" : \"" + this.getPickup() + "\"}";
    }

}

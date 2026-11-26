package model;

import java.io.Serializable;

public class Product implements Serializable {
    private String description;
    private double price;
    private String trackId;

    public Product(String description, double price) {
        this(description, price, null);
    }

    public Product(String description, double price, String trackId) {
        this.description = description;
        this.price = price;
        this.trackId = trackId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getCode() {
        return trackId;
    }

    public void setCode(String code) {
        this.trackId = code;
    }

    public double getTotalPrice(int quantity) {
        return price * quantity;
    }
}

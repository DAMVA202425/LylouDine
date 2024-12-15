package com.example.lyloudinev2;

public class DeliveryOrder {
    private long id;
    private String orderNumber;
    private String customerName;
    private String address;
    private String phone;
    private String deliveryTime;
    private double price;
    private String status;

    // Constructor
    public DeliveryOrder(long id, String orderNumber, String customerName, String address, String phone, String deliveryTime, double price, String status) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.address = address;
        this.phone = phone;
        this.deliveryTime = deliveryTime;
        this.price = price;
        this.status = status;
    }

    // Getters and setters
    public long getId() { return id; }
    public String getOrderNumber() { return orderNumber; }
    public String getCustomerName() { return customerName; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getDeliveryTime() { return deliveryTime; }
    public double getPrice() { return price; }
    public String getStatus() { return status; }

    public void setId(long id) { this.id = id; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setAddress(String address) { this.address = address; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setDeliveryTime(String deliveryTime) { this.deliveryTime = deliveryTime; }
    public void setPrice(double price) { this.price = price; }
    public void setStatus(String status) { this.status = status; }
}

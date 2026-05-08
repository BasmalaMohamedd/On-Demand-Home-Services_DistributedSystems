package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "Bookings")
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long customerId;
    String customerUsername;
    Long providerId;
    Long serviceId;
    int price;
    String status;
    public Booking(){

    }

    public Booking(Long customerId, String customerUsername, Long providerId, Long ServiceId, int price)
    {
        this.customerId = customerId;
        this.customerUsername = customerUsername;
        this.providerId = providerId;
        this.serviceId = serviceId;
        this.price = price;
        this.status = "PENDING";
    }
    public Booking(Long customerId, String customerUsername, Long providerId, Long ServiceId, int price, String status)
    {
        this.customerId = customerId;
        this.customerUsername = customerUsername;
        this.providerId = providerId;
        this.serviceId = serviceId;
        this.price = price;
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }
    public String getCustomerUsername() {
        return customerUsername;
    }
    public Long getId() {
        return id;
    }
    public int getPrice() {
        return price;
    }
    public Long getProviderId() {
        return providerId;
    }
    public Long getServiceId() {
        return serviceId;
    }
    public String getStatus() {
        return status;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }
    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
}

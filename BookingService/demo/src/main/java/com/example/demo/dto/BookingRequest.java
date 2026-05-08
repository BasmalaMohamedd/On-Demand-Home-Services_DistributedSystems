package com.example.demo.dto;

import lombok.Data;

@Data
public class BookingRequest{
    Long customerId;
    String customerUsername;
    Long providerId;
    Long serviceId;
    int price;
    Long bookingId;

    public BookingRequest(Long bookingId, Long customerId, String customerUsername, Long providerId, Long serviceId, int price)
    {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.customerUsername = customerUsername;
        this.providerId = providerId;
        this.serviceId = serviceId;
        this.price = price;
    }

    

    public BookingRequest(){

    }

    public Long getCustomerId() {
        return customerId;
    }
    public String getCustomerUsername() {
        return customerUsername;
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

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
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

    public Long getBookingId(){
        return bookingId;
    }

    public void setBookingId(Long bookingId)
    {
        this.bookingId = bookingId;
    }
}


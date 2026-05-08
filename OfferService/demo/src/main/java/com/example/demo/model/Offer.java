package com.example.demo.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long providerId;
    private boolean isAvailable;
    private int price;
    private String category;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date availableDate;
    private String details;

    public Offer(){}
    public Offer(Long id, Long providerId, boolean isAvailable, int price, String category, Date availableDate, String details)
    {
        this.id = id;
        this.providerId = providerId;
        this.isAvailable = isAvailable;
        this.price = price;
        this.category = category;
        this.availableDate = availableDate;
        this.details = details;
    }
    
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    public void setAvailableDate(Date availableDate) {
        this.availableDate = availableDate;
    }
    public void setCategory(String category) {
        this.category = category;
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

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getAvailableDate() {
        return availableDate;
    }
    public String getCategory() {
        return category;
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

    public boolean isAvailable(){
        return isAvailable;
    }

    public String getDetails() {
        return details;
    }
    
    
}

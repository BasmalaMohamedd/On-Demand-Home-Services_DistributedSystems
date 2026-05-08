package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long senderid;
    Integer amount;
    String type;
    Long bookingId;
    Long receiverId;

    public Transaction(){}
    public Transaction(Long senderId, Long receiverId, Integer amount, String type, Long bookingId)
    {
        this.senderid = senderId;
        this.amount = amount;
        this.type = type;
        this.bookingId = bookingId;
        this.receiverId = receiverId;


    }

    public Integer getAmount() {
        return amount;
    }
    public Long getBookingId() {
        return bookingId;
    }
    public Long getId() {
        return id;
    }
    public Long getReceiverId() {
        return receiverId;
    }
    public Long getSenderid() {
        return senderid;
    }
    public String getType() {
        return type;
    }


}

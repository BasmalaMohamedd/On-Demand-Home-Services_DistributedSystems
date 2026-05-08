package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private Long receiverId;
    private String msg;

    public Notification(){}
    public Notification(Long receiverId, String msg)
    {
        this.receiverId = receiverId;
        this.msg = msg;
    }

    public Long getId() {
        return id;
    }
    public String getMsg() {
        return msg;
    }
    public Long getReceiverId() {
        return receiverId;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }
    
}

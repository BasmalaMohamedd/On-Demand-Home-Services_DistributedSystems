package com.example.demo.dto;

import lombok.Data;

@Data
public class NotificationRequest {
    private Long receiverId;
    private String msg;

    public NotificationRequest(){}
    public NotificationRequest(Long receiverId, String msg)
    {
        this.receiverId = receiverId;
        this.msg = msg;
    }

    
    public String getMsg() {
        return msg;
    }
    public Long getReceiverId() {
        return receiverId;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }
}

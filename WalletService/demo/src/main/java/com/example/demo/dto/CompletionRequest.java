package com.example.demo.dto;

import lombok.Data;

@Data
public class CompletionRequest {
    Long bookingId;

    public CompletionRequest(){}
    public CompletionRequest(Long bookingId)
    {
        this.bookingId = bookingId;
    }
    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
    public Long getBookingId() {
        return bookingId;
    }
    
}

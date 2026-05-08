package com.example.demo.dto;

import lombok.Data;

@Data
public class RollbackRequest {
    Long bookingId;

    public RollbackRequest(){}
    public RollbackRequest(Long bookingId)
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

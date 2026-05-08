package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.BookingRequest;
import com.example.demo.model.Booking;
import com.example.demo.service.BookingService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    BookingService bookingService;
    UserService userService;
    private final String SECRET_KEY = "YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4eXoxMjM0NTY=";
    public BookingController(BookingService bookingService, UserService userService)
    {
        this.bookingService = bookingService;
    }

    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<String> createBooking(@RequestBody BookingRequest booking) 
    {
        bookingService.createBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body("Booking created successfully");
    }

    @GetMapping("/getHistory")
    public List<Booking> getCustomerBookings(@RequestHeader(value = "Authorization", required = false) String authHeader)
    {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing or invalid Authorization token");
        }
        String token = authHeader.substring(7);
        Long customerId = userService.findUserByToken(token, SECRET_KEY);
        return bookingService.getBookingsHistoryForCustomer(customerId);

    }

    @GetMapping("/getCompletedServices")
    public List<Booking> getProviderCompletedServices(@RequestHeader(value = "Authorization", required = false) String authHeader)
    {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing or invalid Authorization token");
        }
        String token = authHeader.substring(7);
        Long providerId = userService.findUserByToken(token, SECRET_KEY);
        return bookingService.getCompletedServicesForProvider(providerId);
    }

    @PostMapping("/completeBookingStatus/{id}")
    public Booking completeBookingStatus(@PathVariable("id") Long id)
    {
        return bookingService.updateBookingStatus(id, "COMPLETED");
    }


    
}

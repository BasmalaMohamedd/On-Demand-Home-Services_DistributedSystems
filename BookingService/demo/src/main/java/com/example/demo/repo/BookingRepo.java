package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Booking;

public interface BookingRepo extends JpaRepository<Booking, Long>{
    Optional<List<Booking>> findByProviderIdAndStatus(Long providerId, String status);
    Optional<List<Booking>> findByCustomerId(Long customerId);

    
}

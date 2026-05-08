package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Offer;

public interface OfferRepo extends JpaRepository<Offer, Long>{
    Optional<List<Offer>> findAllByIsAvailable(boolean isAvailable);
    Optional<List<Offer>> findAllByIsAvailableAndProviderId(boolean isAvailable, Long providerId);
    Optional<List<Offer>> findAllByCategory(String category);

}

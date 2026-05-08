package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Offer;
import com.example.demo.repo.OfferRepo;

@Service
public class OfferService {
    private OfferRepo repo;
    CategoryService categoryService;
    public OfferService(OfferRepo repo, CategoryService categoryService)
    {
        this.repo = repo;
        this.categoryService = categoryService;
    }

    public Offer addOffer(Offer offer)
    {
        if(categoryService.isValidCategory(offer.getCategory()))
        {
            offer.setCategory(offer.getCategory().toLowerCase());
            return repo.save(offer);
        }
        return null;  
    }

    public List<Offer> getActiveOffers(){
        return repo.findAllByIsAvailable(true).orElse(null);
    }
    public List<Offer> getActiveOffersForProvider(Long id){
        return repo.findAllByIsAvailableAndProviderId(true, id).orElse(null);
    }

    public Offer updatePrice(Long serviceId, int value)
    {
        Offer offer = repo.findById(serviceId).orElseThrow(null);
        offer.setPrice(value);
        return repo.save(offer);
    }
    public Offer updatePrice(Long serviceId, boolean isAvailable)
    {
        Offer offer = repo.findById(serviceId).orElseThrow(null);
        offer.setAvailable(isAvailable);
        return repo.save(offer);
    }

    public List<Offer> getOffersByCategory(String category)
    {
        return repo.findAllByCategory(category).orElse(getActiveOffers());
    }

    


}

package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Offer;
import com.example.demo.service.OfferService;

@RestController
@RequestMapping("/offer_service")
public class OfferController {

    OfferService offerService;
    public OfferController(OfferService offerService)
    {
        this.offerService = offerService;
    }


     @PostMapping("/create")
     public Offer createOffer(@RequestBody Offer offer)
     {
        return offerService.addOffer(offer);
     }

     @GetMapping("/{id}")
public List<Offer> getActiveOffersForProvider(@PathVariable("id") Long id) {
    return offerService.getActiveOffersForProvider(id);
}

     @PostMapping("/update/price")
     public Offer updateOfferPrice(@RequestBody updatePriceRequest updatePriceRequest)
     {
        return offerService.updatePrice(updatePriceRequest.serviceId(), updatePriceRequest.price());
     }
     @PostMapping("/update/availability")
     public Offer updateOfferPrice(@RequestBody UpdateAvailabilityRequest updateAvailabilityRequest)
     {
        return offerService.updatePrice(updateAvailabilityRequest.serviceId(), updateAvailabilityRequest.isAvailable());
     }

     @GetMapping("/getByCategory/{category}")
     public List<Offer> getByCategory(@PathVariable("category") String category)
     {
        return offerService.getOffersByCategory(category);
     }


}

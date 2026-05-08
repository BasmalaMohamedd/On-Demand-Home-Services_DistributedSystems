package com.example.demo.service;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.config.RabbitMQConfig;
import com.example.demo.dto.BookingRequest;
import com.example.demo.dto.NotificationRequest;
import com.example.demo.model.Booking;
import com.example.demo.repo.BookingRepo;

import com.rabbitmq.client.ConnectionFactory;

@Service
public class BookingService {
    
    ConnectionFactory factory = new ConnectionFactory();
    private final RabbitTemplate rabbitTemplate;
    BookingRepo repo;
    public BookingService(BookingRepo repo, RabbitTemplate rabbitTemplate)
    {
        this.repo = repo;
        this.rabbitTemplate = rabbitTemplate;
        factory.setHost("localhost");
    }


    //service provider functions
    public List<Booking> getCompletedServicesForProvider(Long providerId){
        return repo.findByProviderIdAndStatus(providerId, "COMPLETED").orElse(null);
    }


    public Booking updateBookingStatus(Long bookingId, String status)
    {
        Booking booking = repo.findById(bookingId).orElse(null);
        if(booking != null)
        {
            booking.setStatus(status);
            return repo.save(booking);
        }
        return null;
    }

    Booking getBookingById(Long id){
        return repo.findById(id).orElse(null);
    }

    public Booking addBooking(Booking booking)
    {
        return this.repo.save(booking);
    }


    //customer functions
    //consumer
    public void createBooking(BookingRequest bookingRequest)
    {
        Booking booking = new Booking(
            bookingRequest.getCustomerId(),
            bookingRequest.getCustomerUsername(),
            bookingRequest.getProviderId(),
            bookingRequest.getServiceId(),
            bookingRequest.getPrice()
        );
        this.addBooking(booking);
        bookingRequest.setBookingId(booking.getId());
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.EXCHANGE,
            "routing_key_wallet", 
            bookingRequest
        );
        

        System.out.println(String.format("Json message sent " +  bookingRequest));

        System.out.println("Booking request sent to queue");

    }


    

    @RabbitListener(queues = RabbitMQConfig.BOOKING_COMPLETION_QUEUE)
    public void compeleteBooking(Long bookingId)
    {
        Booking booking = updateBookingStatus(bookingId, "CONFIRMED");
        notifyCustomer(booking.getCustomerId(), booking);
        notifyProvider(booking.getProviderId(), booking);

    }

    @RabbitListener(queues = RabbitMQConfig.ROLLBACK_QUEUE)
    public void rejectBooking(Long bookingId)
    {
        Booking booking = updateBookingStatus(bookingId, "REJECTED");
        notifyCustomer(booking.getCustomerId(), booking);

    }

    
    public List<Booking> getBookingsHistoryForCustomer(Long customerId){
        return repo.findByCustomerId(customerId).orElse(null);
    }

    private void notifyCustomer(Long id, Booking booking){
        String msg = "Hello " +  booking.getCustomerUsername() + " your booking of id: " + booking.getId() + "is: ";
        if(booking.getStatus().equals("CONFIRMED"))
        {
            msg += "confirmed";
        }
        else
        {
            msg += "rejected, insufficient balance";
        }

        NotificationRequest notificationRequest = new NotificationRequest(id, msg);
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.EXCHANGE, 
            "routing_key_notification",
            notificationRequest
        );

        
    }
    private void notifyProvider(Long id, Booking booking){
        String msg = "Hello Customer with name: " + booking.getCustomerUsername() + " has successfully confirmed booking your service with booking id " + booking.getId() + "!";
        NotificationRequest notificationRequest = new NotificationRequest(id, msg);
         rabbitTemplate.convertAndSend(
            RabbitMQConfig.EXCHANGE, 
            "routing_key_notification",
            notificationRequest
        );

    }




}

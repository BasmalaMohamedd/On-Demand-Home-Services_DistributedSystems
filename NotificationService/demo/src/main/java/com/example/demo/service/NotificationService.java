package com.example.demo.service;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.config.RabbitMQConfig;
import com.example.demo.dto.NotificationRequest;
import com.example.demo.model.Notification;
import com.example.demo.repo.NotificationRepo;

@Service
public class NotificationService {
    private final NotificationRepo repo;
    private final RabbitTemplate rabbitTemplate;

    public NotificationService(NotificationRepo repo, RabbitTemplate rabbitTemplate)
    {
        this.repo = repo;
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<Notification> getUserNotifications(Long receiverId)
    {
        return repo.findByReceiverId(receiverId).orElse(null);
    }

    public Notification getNotification(Long id)
    {
        return repo.findById(id).orElse(null);
    }


    @RabbitListener(queues =  RabbitMQConfig.NOTIFICATION_QUEUE)
    public void receiveNotification(NotificationRequest notificationRequest){
        Notification notification = new Notification(notificationRequest.getReceiverId(), notificationRequest.getMsg());
        repo.save(notification);

    }

    

    
}

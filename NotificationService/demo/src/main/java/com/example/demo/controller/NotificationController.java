package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Notification;
import com.example.demo.service.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    NotificationService notificationService;
    public NotificationController(NotificationService notificationService)
    {
        this.notificationService = notificationService;
    }
    


    @GetMapping("/getNotification/{id}")
    public Notification getNotificationById(@PathVariable("id") Long id)
    {
        return notificationService.getNotification(id);

    }
    
    @GetMapping("/getNotifcations/{id}")
    public List<Notification> getUserNotifications(@PathVariable("id") Long id){
        return notificationService.getUserNotifications(id);


    }
    
}

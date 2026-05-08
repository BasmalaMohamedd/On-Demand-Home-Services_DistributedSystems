package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Notification;

public interface NotificationRepo extends JpaRepository<Notification, Long>{
    Optional<List<Notification>> findByReceiverId(Long receiverId);

    
} 
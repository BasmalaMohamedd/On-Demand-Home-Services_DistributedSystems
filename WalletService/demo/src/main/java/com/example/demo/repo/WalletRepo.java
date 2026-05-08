package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Wallet;

public interface WalletRepo extends JpaRepository<Wallet, Long>{
    Optional<Wallet> findByUserId(Long userId);
}

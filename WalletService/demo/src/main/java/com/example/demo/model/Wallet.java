package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "wallets")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long userId;
    Integer balance;
    public Wallet(){}
    public Wallet(Long userId, Integer balance)
    {
        this.balance = balance;
        this.userId = userId;
    }
    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Integer getBalance() {
        return balance;
    }
    public Long getUserId() {
        return userId;
    }
}

package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends User{
    Integer balance;
    public Customer(){}
    public Customer(String username, String password, String role, Integer balace)
    {
        super(username, password, role);
        this.balance = balace;
    }
    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    public Integer getBalance() {
        return balance;
    }

    @Override
    public String getRole() {
        return "CUSTOMER";
    }
}

package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User{
    public Admin(){}
    public Admin(String username, String password, String role){
        super(username, password, role);

    }

    @Override
    public String getRole() {
        return "ADMIN";
    }
    
}

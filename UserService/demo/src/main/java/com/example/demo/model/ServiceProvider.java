package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SERVICE_PROVIDER")
public class ServiceProvider extends User{
    private String professionType;
    public ServiceProvider(){}
    public ServiceProvider(String username, String password, String role, String professionType)
    {
        super(username, password, role);
        this.professionType = professionType;
    }

    public void setProfessionType(String professionType) {
        this.professionType = professionType;
    }

    public String getProfessionType() {
        return professionType;
    }


    @Override
    public String getRole() {
        return "SERVICE_PROVIDER";
    }
    
}

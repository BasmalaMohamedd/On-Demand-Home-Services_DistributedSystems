package com.example.demo.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long>{
    Optional<Category> findByName(String name);
    
}

package com.example.demo.service;


import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repo.CategoryRepo;

@Service
public class CategoryService {
    private CategoryRepo repo;
    public CategoryService(CategoryRepo repo)
    {
        this.repo = repo;
    }

    public Category addCategory(Category category)
    {
        // category.setName(category.getName().toLowerCase());
        return repo.save(category);
    }

    public boolean isValidCategory(String category)
    {
        Category cat = repo.findByName(category.toLowerCase()).orElse(null);
        return cat != null;
    }
}

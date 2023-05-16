package com.buy.r2sshop.service;

import com.buy.r2sshop.entity.Category;
import com.buy.r2sshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    public Category getCategoryByName(String categoryName){
        Category category = categoryRepository.findByName(categoryName);
        return category;
    }
}

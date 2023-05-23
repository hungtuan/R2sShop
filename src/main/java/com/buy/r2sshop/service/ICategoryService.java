package com.buy.r2sshop.service;

import com.buy.r2sshop.entity.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategories();

    Category getCategoryByName(String categoryName);
}


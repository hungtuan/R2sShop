package com.buy.r2sshop.controller;

import com.buy.r2sshop.entity.Category;
import com.buy.r2sshop.service.CategoryService;
import com.buy.r2sshop.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> category = categoryService.getAllCategories();
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}

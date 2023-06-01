package com.buy.r2sshop.repository;

import com.buy.r2sshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAll();

    Category findByName(String name);

    Category findCategoryById(Integer categoryId);
}

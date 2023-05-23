package com.buy.r2sshop.service;

import com.buy.r2sshop.entity.Category;
import com.buy.r2sshop.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProductsByCategoryWithPaging(Category category, int page, int pageSize);

    Product getProductById(int productId);
}


package com.buy.r2sshop.controller;

import com.buy.r2sshop.entity.Category;
import com.buy.r2sshop.entity.Product;
import com.buy.r2sshop.entity.VariantProduct;
import com.buy.r2sshop.service.CategoryService;
import com.buy.r2sshop.service.ProductService;
import com.buy.r2sshop.service.VariantProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ProductController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @Autowired
    private VariantProductService variantProductService;

    //Lấy product theo category name
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProductsByCategory(@RequestParam("category") String categoryName,
                                                                  @RequestParam("page") int page,
                                                                  @RequestParam("pageSize") int pageSize) {
        Category category = categoryService.getCategoryByName(categoryName);
        List<Product> products = productService.getProductsByCategoryWithPaging(category, page, pageSize);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //Lấy product theo id
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    Lấy biến thế product VariantProduct
    @GetMapping("/products/{productId}/variants")
    public ResponseEntity<List<VariantProduct>> getVariantsByProduct(@PathVariable("productId") Integer productId) {
        Product product = productService.getProductById(productId);
        List<VariantProduct> variants = variantProductService.getVariantsByProduct(product);
        return new ResponseEntity<>(variants, HttpStatus.OK);
    }


}

package com.buy.r2sshop.controller;

import com.buy.r2sshop.entity.Category;
import com.buy.r2sshop.entity.Product;
import com.buy.r2sshop.entity.VariantProduct;
import com.buy.r2sshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ProductController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IProductService productService;

    @Autowired
    private IVariantProductService variantProductService;

    //Lấy product theo category name
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProductsByCategory(@RequestParam("category") String categoryName,
                                                                  @RequestParam("page") int page,
                                                                  @RequestParam("pageSize") int pageSize) {
        Category category = categoryService.getCategoryByName(categoryName);
        List<Product> products = productService.getProductsByCategoryWithPaging(category, page, pageSize);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //Lấy product theo id
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
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
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/products/{productId}/variants")
    public ResponseEntity<List<VariantProduct>> getVariantsByProduct(@PathVariable("productId") Integer productId) {
        Product product = productService.getProductById(productId);
        List<VariantProduct> variants = variantProductService.getVariantsByProduct(product);
        return new ResponseEntity<>(variants, HttpStatus.OK);
    }

    //

    @PostMapping("/product")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> addProduct(@RequestBody Product product, @RequestParam("categoryId") Integer categoryId) {
        Product addedProduct = productService.addProduct(product, categoryId);
        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }


    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId, @RequestBody Product updatedProduct) {
        Product updated = productService.updateProduct(productId, updatedProduct);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{productId}/variants")
    public ResponseEntity<VariantProduct> addVariantProduct(@PathVariable Integer productId, @RequestBody VariantProduct variantProduct) {
        VariantProduct addedVariantProduct = variantProductService.addVariantProduct(productId, variantProduct);
        return new ResponseEntity<>(addedVariantProduct, HttpStatus.CREATED);
    }

    @PutMapping("/variants/{variantProductId}")
    public ResponseEntity<VariantProduct> updateVariantProduct(@PathVariable Integer variantProductId, @RequestBody VariantProduct updatedVariantProduct) {
        VariantProduct updated = variantProductService.updateVariantProduct(variantProductId, updatedVariantProduct);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/variants/{variantProductId}")
    public ResponseEntity<Void> deleteVariantProduct(@PathVariable Integer variantProductId) {
        variantProductService.deleteVariantProduct(variantProductId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

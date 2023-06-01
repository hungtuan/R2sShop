package com.buy.r2sshop.service;

import com.buy.r2sshop.entity.Category;
import com.buy.r2sshop.entity.Product;
import com.buy.r2sshop.repository.CategoryRepository;
import com.buy.r2sshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<Product> getProductsByCategoryWithPaging(Category category, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Product> productPage = productRepository.findByCategory(category, pageable);
        return productPage.getContent();
    }

    @Override
    public Product getProductById(int productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional.orElse(null);
    }

    @Override
    public Product addProduct(Product product, Integer categoryId) {
        Category category = categoryRepository.findCategoryById(categoryId); // Get the category by ID from repository
        product.setCategory(category);
        return productRepository.save(product);
    }

    //

    public Product updateProduct(Integer productId, Product updatedProduct) {
        Product existingProduct = getProductById(productId);
        // Cập nhật các trường dữ liệu của existingProduct với updatedProduct
        // Ví dụ: existingProduct.setName(updatedProduct.getName());
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setProduct_image_url(updatedProduct.getProduct_image_url());

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Integer productId) {
        Product existingProduct = getProductById(productId);
        productRepository.delete(existingProduct);
    }

}

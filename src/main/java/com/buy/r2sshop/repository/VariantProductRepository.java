package com.buy.r2sshop.repository;

import com.buy.r2sshop.entity.Product;
import com.buy.r2sshop.entity.VariantProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VariantProductRepository extends CrudRepository<VariantProduct, Integer> {
    List<VariantProduct> findByProduct(Product product);
}
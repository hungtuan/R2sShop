package com.buy.r2sshop.service;

import com.buy.r2sshop.entity.Product;
import com.buy.r2sshop.entity.VariantProduct;
import com.buy.r2sshop.repository.VariantProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VariantProductService {
    @Autowired
    private VariantProductRepository variantProductRepository;

    public List<VariantProduct> getVariantsByProduct(Product product) {
        return variantProductRepository.findByProduct(product);
    }
}

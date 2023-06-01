package com.buy.r2sshop.service;

import com.buy.r2sshop.entity.Product;
import com.buy.r2sshop.entity.VariantProduct;
import com.buy.r2sshop.repository.VariantProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantProductService implements IVariantProductService {
    @Autowired
    private final VariantProductRepository variantProductRepository;
    @Autowired
    private final ProductService productService;

    public VariantProductService(VariantProductRepository variantProductRepository, ProductService productService) {
        this.variantProductRepository = variantProductRepository;
        this.productService = productService;
    }

    @Override
    public List<VariantProduct> getVariantsByProduct(Product product) {
        return variantProductRepository.findByProduct(product);
    }

    //
    public VariantProduct addVariantProduct(Integer productId, VariantProduct variantProduct) {
        Product product = productService.getProductById(productId);
        variantProduct.setProduct(product);
        return variantProductRepository.save(variantProduct);
    }

    public VariantProduct updateVariantProduct(Integer variantProductId, VariantProduct updatedVariantProduct) {
        VariantProduct existingVariantProduct = getVariantProductById(variantProductId);
        // Cập nhật các trường dữ liệu của existingVariantProduct với updatedVariantProduct
        return variantProductRepository.save(existingVariantProduct);
    }

    public void deleteVariantProduct(Integer variantProductId) {
        VariantProduct existingVariantProduct = getVariantProductById(variantProductId);
        variantProductRepository.delete(existingVariantProduct);
    }

    private VariantProduct getVariantProductById(Integer variantProductId) {
        return variantProductRepository.findById(variantProductId)
                .orElseThrow(() -> new RuntimeException("Not found variantProductId" + variantProductId));
    }




}

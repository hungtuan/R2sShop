package com.buy.r2sshop.service;

import com.buy.r2sshop.entity.Product;
import com.buy.r2sshop.entity.VariantProduct;

import java.util.List;

public interface IVariantProductService {
    List<VariantProduct> getVariantsByProduct(Product product);

    VariantProduct addVariantProduct(Integer productId, VariantProduct variantProduct);

    VariantProduct updateVariantProduct(Integer variantProductId, VariantProduct updatedVariantProduct);

    void deleteVariantProduct(Integer variantProductId);
}

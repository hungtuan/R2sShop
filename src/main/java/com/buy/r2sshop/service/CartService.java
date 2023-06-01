package com.buy.r2sshop.service;

import com.buy.r2sshop.entity.Cart;
import com.buy.r2sshop.entity.CartLineItem;
import com.buy.r2sshop.entity.Product;
import com.buy.r2sshop.entity.VariantProduct;
import com.buy.r2sshop.repository.CartLineItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartLineItemRepository cartLineItemRepository;

    @Autowired
    public CartService(CartLineItemRepository cartLineItemRepository) {
        this.cartLineItemRepository = cartLineItemRepository;
    }

    public CartLineItem addProductToCart(Cart cart, VariantProduct variantProduct, int quantity) {
        CartLineItem cartLineItem = new CartLineItem();
        cartLineItem.setCart(cart);
        cartLineItem.setVariantProduct(variantProduct);
        cartLineItem.setQuantity(quantity);

        // Tính toán giá của cart line item (ví dụ: product.getPrice() * quantity)

        return cartLineItemRepository.save(cartLineItem);
    }

    // Các phương thức tùy chọn khác liên quan đến Cart Line Item và tính toán tổng giá
}

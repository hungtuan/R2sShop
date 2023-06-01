package com.buy.r2sshop.controller;

import com.buy.r2sshop.entity.Cart;
import com.buy.r2sshop.entity.CartLineItem;
import com.buy.r2sshop.entity.Product;
import com.buy.r2sshop.entity.VariantProduct;
import com.buy.r2sshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart/{cartId}/addProduct")
    public ResponseEntity<CartLineItem> addProductToCart(
            @PathVariable("cartId") Long cartId,
            @RequestBody VariantProduct variantProduct,
            @RequestParam("quantity") int quantity) {

        // Gọi service để thêm vào cart và tạo cart line item
        Cart cart = new Cart();
        CartLineItem cartLineItem = cartService.addProductToCart(cart, variantProduct, quantity);

        return new ResponseEntity<>(cartLineItem, HttpStatus.CREATED);
    }


}

package com.buy.r2sshop.repository;

import com.buy.r2sshop.entity.Cart;
import com.buy.r2sshop.entity.CartLineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartLineItemRepository extends JpaRepository<CartLineItem, Long> {
    // Các phương thức tùy chọn khác liên quan đến Cart Line Item
}


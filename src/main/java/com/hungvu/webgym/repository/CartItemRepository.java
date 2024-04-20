package com.hungvu.webgym.repository;

import com.hungvu.webgym.model.Cart;
import com.hungvu.webgym.model.CartItem;
import com.hungvu.webgym.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartAndProduct(Cart cart, Product Product);
}

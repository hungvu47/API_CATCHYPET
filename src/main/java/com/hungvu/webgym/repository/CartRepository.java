package com.hungvu.webgym.repository;

import com.hungvu.webgym.model.Cart;
import com.hungvu.webgym.model.CartItem;
import com.hungvu.webgym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);

    @Query("SELECT c FROM CartItem c WHERE c.cartItemId = :cartItemId")
    CartItem findCartItemById(Long cartItemId);

    @Query("SELECT c FROM Cart c WHERE c.user.email = :email")
    Optional<Cart> findByEmail(String email);
}

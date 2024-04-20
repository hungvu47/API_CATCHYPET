package com.hungvu.webgym.service;

import com.hungvu.webgym.model.Cart;
import com.hungvu.webgym.model.CartItem;
import com.hungvu.webgym.request.CartItemRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICartService {
    Cart getUserCart(String email);

    List<CartItem> getAllCartItemsByUser(String email);

    CartItem addToCart(CartItemRequest itemRequest, String email);

    CartItem updateCartItem( Long cartItemId, CartItemRequest itemRequest);

    void removeCartItem(Long cartItemId);
}

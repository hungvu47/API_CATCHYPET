package com.hungvu.webgym.controller;

import com.hungvu.webgym.model.Cart;
import com.hungvu.webgym.model.CartItem;
import com.hungvu.webgym.model.User;
import com.hungvu.webgym.request.CartItemRequest;
import com.hungvu.webgym.service.ICartService;
import com.hungvu.webgym.service.WebUserDetailsService;
import com.sun.security.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final ICartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartItem> addToCart(@RequestBody CartItemRequest itemRequest,
                                              Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = ((UserDetails) authentication.getPrincipal()).getUsername();
            CartItem addedItem = cartService.addToCart(itemRequest, email);
            if (addedItem != null) {
                return ResponseEntity.ok(addedItem);
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/items")
    public ResponseEntity<List<CartItem>> viewCart(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = ((UserDetails) authentication.getPrincipal()).getUsername();
            List<CartItem> cartItems = cartService.getAllCartItemsByUser(email);
            return ResponseEntity.ok(cartItems);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<CartItem> updateCart (@RequestBody CartItemRequest itemRequest,
                                                @PathVariable Long cartItemId) {
        try {
                CartItem updatedCartItem = cartService.updateCartItem( cartItemId, itemRequest);
                if (updatedCartItem != null) {
                    return ResponseEntity.ok(updatedCartItem);
                 }else {
                    return ResponseEntity.badRequest().build();
            }
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable Long cartItemId) {
        try {
            cartService.removeCartItem(cartItemId);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi xóa item");
        }
    }

}

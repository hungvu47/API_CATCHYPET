package com.hungvu.webgym.service.impl;

import com.hungvu.webgym.model.Cart;
import com.hungvu.webgym.model.CartItem;
import com.hungvu.webgym.model.Product;
import com.hungvu.webgym.model.User;
import com.hungvu.webgym.repository.CartItemRepository;
import com.hungvu.webgym.repository.CartRepository;
import com.hungvu.webgym.repository.ProductRepository;
import com.hungvu.webgym.repository.UserRepository;
import com.hungvu.webgym.request.CartItemRequest;
import com.hungvu.webgym.service.ICartService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements ICartService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;

    public CartServiceImpl(ProductRepository productRepository, CartRepository cartRepository, UserRepository userRepository, CartItemRepository cartItemRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
    }


    @Override
    public Cart getUserCart(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }
        return cartRepository.findByUser(user);
    }

    @Override
    public List<CartItem> getAllCartItemsByUser(String email) {
        Optional<Cart> optionalCart = cartRepository.findByEmail(email);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            return cart.getCartItems();
        } else {
            // Return an empty list or handle the case where the user has no cart
            return Collections.emptyList();
        }
    }

//    @Override
//    public void addToCart(CartItemRequest itemRequest, String email) {
//        Optional<User> userOptional  = userRepository.findByEmail(email);
//        if (userOptional.isEmpty()) {
//            throw new RuntimeException("User not found");
//        }
//        User user = userOptional.get();
//        Cart cart = cartRepository.findByUser(user);
//        if (cart == null) {
//            cart = new Cart();
//            cart.setUser(user);
//        }
//        Product product = productRepository.findById(itemRequest.getProductId())
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//
//        CartItem existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);
//        if ( existingCartItem != null) {
//            existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
//        } else  {
//            CartItem cartItem = new CartItem();
//            cartItem.setProduct(product);
//            cartItem.setQuantity(itemRequest.getQuantity());
//            cartItem.setCart(cart);
//
//            cart.getCartItems().add(cartItem);
//        }
//        cartRepository.save(cart);;
//    }

    @Override
    public CartItem addToCart(CartItemRequest itemRequest, String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userOptional.get();
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
        }
        Product product = productRepository.findById(itemRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);
        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
            cartItemRepository.save(existingCartItem);
            return existingCartItem;
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(itemRequest.getQuantity());
            cartItem.setCart(cart);
            cart.getCartItems().add(cartItem);
            cartItemRepository.save(cartItem);
            cartRepository.save(cart);
            return cartItem;
        }
    }

    @Override
    public CartItem updateCartItem(Long cartItemId, CartItemRequest itemRequest) {
        CartItem cartItem = cartRepository.findCartItemById(cartItemId);
        if (cartItem != null) {
            cartItem.setQuantity(itemRequest.getQuantity());
            cartItemRepository.save(cartItem);
//            cartRepository.save(cartItem.getCart());

            return cartItem;
        }
        return null;
    }

    @Override
    public void removeCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

}

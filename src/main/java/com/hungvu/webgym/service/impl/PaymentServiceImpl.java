package com.hungvu.webgym.service.impl;

import com.hungvu.webgym.model.*;
import com.hungvu.webgym.repository.*;
import com.hungvu.webgym.request.OrderRequest;
import com.hungvu.webgym.service.IPaymentService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentServiceImpl implements IPaymentService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final OrderDetailRepository detailRepository;


    public PaymentServiceImpl(OrderRepository orderRepository,
                              CartRepository cartRepository,
                              UserRepository userRepository,
                              AddressRepository addressRepository,
                              OrderDetailRepository detailRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.detailRepository = detailRepository;
    }


    @Override
    public Order processPayment(Authentication authentication, OrderRequest orderRequest) {
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user);

        Address address = addressRepository.findById(orderRequest.getAddressId())
                .orElseThrow(() -> new RuntimeException("Address not found"));

        Order order = saveOder(cart);
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        order.setShippingAddress(address);
        orderRepository.save(order);

        cart.getCartItems().clear();

        cartRepository.save(cart);

        return order;
    }

    @Override
    public Order saveOder(Cart cart) {

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderDate(new Date());
        order.setTotalAmount(calculateTotalAmount(cart));
        order.setOrderStatus(true); // Đã thanh toán

        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItems()) {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrder(order);
            orderDetails.setProduct(cartItem.getProduct());
            orderDetails.setQuantity(cartItem.getQuantity());
            orderDetailsList.add(orderDetails);
        }
        order.setOrderDetails(orderDetailsList);

        return order;
    }

    @Override
    public List<Order> getUserOder(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("User not found"));
        return orderRepository.findByUser(user);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public List<OrderDetails> getOrderDetailsByOrderId(Long orderId) {
        return detailRepository.findByOrderId(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    private double calculateTotalAmount(Cart cart) {
        double totalAmount = 0.0;
        for (CartItem cartItem : cart.getCartItems()) {
            totalAmount += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        return totalAmount;
    }


}

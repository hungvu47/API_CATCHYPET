package com.hungvu.webgym.service;

import com.hungvu.webgym.model.Address;
import com.hungvu.webgym.model.Cart;
import com.hungvu.webgym.model.Order;
import com.hungvu.webgym.model.OrderDetails;
import com.hungvu.webgym.request.OrderRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPaymentService {
    Order processPayment(Authentication authentication, OrderRequest orderRequest);

    Order saveOder(Cart cart);

    List<Order> getUserOder(String email);

    Order getOrderById(Long orderId);

    List<OrderDetails> getOrderDetailsByOrderId(Long orderId);

    List<Order> getAllOrders();
}

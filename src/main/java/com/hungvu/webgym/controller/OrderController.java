package com.hungvu.webgym.controller;

import com.hungvu.webgym.model.Order;
import com.hungvu.webgym.model.OrderDetails;
import com.hungvu.webgym.request.OrderRequest;
import com.hungvu.webgym.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final IPaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity<Order> processPayment(Authentication authentication,
                                                 @RequestBody OrderRequest orderRequest) {
        Order ordered = paymentService.processPayment(authentication, orderRequest);
        return ResponseEntity.ok(ordered);
    }

    @GetMapping("/order-of-user")
    public ResponseEntity<List<Order>> getUserOder(Authentication authentication) {
        String email = authentication.getName();
        List<Order> orders = paymentService.getUserOder(email);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/details/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        return paymentService.getOrderById(orderId);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderDetails>> getOrderDetailsByOrderId(@PathVariable Long orderId) {
        List<OrderDetails> orderDetails = paymentService.getOrderDetailsByOrderId(orderId);
        return ResponseEntity.ok().body(orderDetails);
    }

    @GetMapping("/allOrder")
    public ResponseEntity<List<Order>> getAllOrder() {
        List<Order> ordersList = paymentService.getAllOrders();
        return ResponseEntity.ok(ordersList);
    }
}

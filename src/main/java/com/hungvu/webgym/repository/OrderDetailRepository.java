package com.hungvu.webgym.repository;

import com.hungvu.webgym.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetails, Long> {
    @Query("SELECT o FROM OrderDetails o WHERE o.order.orderId = :orderId")
    List<OrderDetails> findByOrderId(Long orderId);
}

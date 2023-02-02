package com.example.services;

import com.example.errors.EntityNotFoundException;
import com.example.persistence.OrderEntity;
import com.example.persistence.OrderRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OrderService {

    private final Log log = LogFactory.getLog(getClass());

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createOrder(OrderEntity newOrder) {
        orderRepository.save(newOrder);
        log.info("Created order with ID " + newOrder.getId());
    }

    public Iterable<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity findOrderById(int orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new EntityNotFoundException("No order found with ID " + orderId));
    }

    public Iterable<OrderEntity> findOrdersByEmail(String email) {
        return orderRepository.findByCustomerEmail(email);
    }

}

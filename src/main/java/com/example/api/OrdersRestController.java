package com.example.api;

import com.example.dto.OrderDTO;
import com.example.persistence.OrderEntity;
import com.example.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api")
public class OrdersRestController {

    Logger logger = LoggerFactory.getLogger(OrdersRestController.class);

    private final OrderService orderService;

    public OrdersRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/orders")
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<OrderDTO> getOrders(Authentication authentication, @RequestParam Optional<String> email) {
        logger.info("Received request from '{}'  with roles {}", authentication.getName(), authentication.getAuthorities());

        Iterable<OrderEntity> result = email.isEmpty()
            ? orderService.getAllOrders()
            : orderService.findOrdersByEmail(email.get());

        List<OrderDTO> dtos = new ArrayList<>();
        for (OrderEntity entity : result) {
            dtos.add(OrderDTO.of(entity));
        }

        return dtos;
    }

    @GetMapping(value = "/orders/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDTO getOrderById(@PathVariable("orderId") Integer orderId) {
        return OrderDTO.of(orderService.findOrderById(orderId));
    }

    @PostMapping(value = "/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDTO newOrderDTO) {
        OrderEntity newOrder = newOrderDTO.toEntity();
        orderService.createOrder(newOrder);
    }
}

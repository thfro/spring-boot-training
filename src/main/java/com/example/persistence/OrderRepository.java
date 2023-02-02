package com.example.persistence;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {
    Iterable<OrderEntity> findByCustomerEmail(String email);
}

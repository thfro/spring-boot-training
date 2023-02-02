package com.example.dto;

import com.example.persistence.OrderEntity;

public class OrderDTO {
    private int id;
    private CustomerDTO customer;

    public OrderDTO() {}

    public OrderDTO(int id, CustomerDTO customer) {
        this.id = id;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }


    public static OrderDTO of(OrderEntity order) {
        CustomerDTO customerDTO = new CustomerDTO(order.getCustomerEmail(), order.getCustomerMobile());
        return new OrderDTO(order.getId(), customerDTO);
    }

    public OrderEntity toEntity() {
        OrderEntity entity = new OrderEntity();
        entity.setCustomerMobile(customer.getMobile());
        entity.setCustomerEmail(customer.getEmail());
        return entity;
    }
}

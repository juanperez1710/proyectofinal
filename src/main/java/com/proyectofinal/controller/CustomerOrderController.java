package com.proyectofinal.controller;

import com.proyectofinal.model.*;
import com.proyectofinal.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerOrderController {

    private final CustomerService customerService;
    private final OrderService orderService;

    public CustomerOrderController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.create(customer));
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestParam Long customerId,
                                             @RequestBody List<OrderItem> items) {
        return ResponseEntity.ok(orderService.createOrder(customerId, items));
    }

    @GetMapping("/customers/{id}/orders")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrdersByCustomer(id));
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}

package com.proyectofinal.service;

import com.proyectofinal.model.*;
import com.proyectofinal.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;
    private final CustomerRepository customerRepo;

    public OrderService(OrderRepository orderRepo, ProductRepository productRepo, CustomerRepository customerRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
        this.customerRepo = customerRepo;
    }

    public Order createOrder(Long customerId, List<OrderItem> items) {
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setCreatedAt(LocalDateTime.now());

        double total = 0;
        for (OrderItem item : items) {
            Product product = productRepo.findById(item.getProduct().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
            if (product.getStock() < item.getQuantity()) {
                throw new IllegalArgumentException("Stock insuficiente para " + product.getName());
            }
            product.setStock(product.getStock() - item.getQuantity());
            productRepo.save(product);

            item.setOrder(order);
            item.setPrice(product.getPrice() * item.getQuantity());
            total += item.getPrice();
        }

        order.setItems(items);
        order.setTotal(total);

        return orderRepo.save(order);
    }

    public List<Order> getOrdersByCustomer(Long customerId) {
        return orderRepo.findByCustomerId(customerId);
    }

    public Order getOrderById(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado"));
    }
}

package com.proyectofinal.service;

import com.proyectofinal.model.Customer;
import com.proyectofinal.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer create(Customer customer) {
        return repository.save(customer);
    }

    public Customer findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con id " + id));
    }
}

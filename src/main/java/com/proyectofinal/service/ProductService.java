package com.proyectofinal.service;

import com.proyectofinal.model.Product;
import com.proyectofinal.repository.ProductRepository;
import com.proyectofinal.dto.ProductRequest;
import com.proyectofinal.dto.ProductResponse;
import com.proyectofinal.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id " + id));
    }

    public Product create(ProductRequest request) {
        if (repository.existsByNameIgnoreCase(request.name())) {
            throw new IllegalArgumentException("El producto con nombre '" + request.name() + "' ya existe");
        }

        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        product.setDescription(request.description());
        product.setStock(request.stock());
        product.setCategory(request.category());
        return repository.save(product);
    }

    public Product update(Long id, ProductRequest request) {
        Product product = findById(id);
        if (!product.getName().equalsIgnoreCase(request.name()) &&
                repository.existsByNameIgnoreCase(request.name())) {
            throw new IllegalArgumentException("El producto con nombre '" + request.name() + "' ya existe");
        }
        product.setName(request.name());
        product.setPrice(request.price());
        product.setDescription(request.description());
        product.setStock(request.stock());
        product.setCategory(request.category());
        return repository.save(product);
    }

    public void delete(Long id) {
        Product product = findById(id);
        repository.delete(product);
    }

    public List<Product> searchByName(String keyword) {
        return repository.findByNameContainingIgnoreCase(keyword);
    }
}

package com.proyectofinal;

import com.proyectofinal.dto.ProductRequest;
import com.proyectofinal.exception.ResourceNotFoundException;
import com.proyectofinal.model.Category;
import com.proyectofinal.model.Product;
import com.proyectofinal.repository.ProductRepository;
import com.proyectofinal.service.ProductService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    ProductRepository repository;

    @InjectMocks
    ProductService service;

    @Nested
    class ReadTests{
        @Test
        void shouldReturnAllProducts(){
            Product p = new Product();
            p.setName("Laptop");
            when(repository.findAll()).thenReturn(List.of(p));
            List<Product> result = service.findAll();

            assertThat(result).hasSize(1);
            assertThat(result.get(0).getName()).isEqualTo("Laptop");
        }

        @Test
        void shouldReturnProductById(){
            Product p = new Product();
            p.setId(1L);
            p.setName("Laptop");
            when(repository.findById(1L)).thenReturn(Optional.of(p));
            Product result= service.findById(1L);

            assertThat(result.getName()).isEqualTo("Laptop");
        }

        @Test
        void shouldThrowWhenProductNotFound() {
            when(repository.findById(99L)).thenReturn(Optional.empty());

            assertThatThrownBy(()->service.findById(99L))
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessageContaining("Producto no encontrado");
        }
    }
    @Nested
    class CreateTests{

        @Test
        void shouldCreateProduct(){
            ProductRequest request=new ProductRequest("Laptop", 2000.0,"Gaming",5, Category.ELECTRONICS);
            Product saved=new Product();
            saved.setId(1L);
            saved.setName("Laptop");

            when(repository.save(any(Product.class))).thenReturn(saved);
            Product result=service.create(request);
            assertThat(result.getId()).isEqualTo(1L);

            ArgumentCaptor<Product> captor=ArgumentCaptor.forClass(Product.class);
            verify(repository).save(captor.capture());
            assertThat(captor.getValue().getName()).isEqualTo("Laptop");
            assertThat(captor.getValue().getPrice()).isEqualTo(2000.0);
        }
        @Test
        void shouldRejectDuplicateName() {
            when(repository.existsByNameIgnoreCase("Laptop")).thenReturn(true);

            ProductRequest request = new ProductRequest("Laptop", 2000.0, "Gaming", 5, Category.ELECTRONICS);

            assertThatThrownBy(() -> service.create(request))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Ya existe");
        }
    }
    @Nested
    class UpdateTests {

        @Test
        void shouldUpdateProduct() {
            Product existing = new Product();
            existing.setId(1L);
            existing.setName("Old Laptop");

            when(repository.findById(1L)).thenReturn(Optional.of(existing));
            when(repository.save(any(Product.class))).thenAnswer(inv -> inv.getArgument(0));

            ProductRequest request = new ProductRequest("New Laptop", 25000.0, "Updated", 10, Category.ELECTRONICS);

            Product result = service.update(1L, request);

            assertThat(result.getName()).isEqualTo("New Laptop");
            assertThat(result.getPrice()).isEqualTo(25000.0);
        }
    }
    @Nested
    class DeleteTests {
        @Test
        void shouldDeleteProduct() {
            Product existing = new Product();
            existing.setId(1L);
            when(repository.findById(1L)).thenReturn(Optional.of(existing));
            service.delete(1L);
            verify(repository).delete(existing);
        }
    }
    @Nested
    class SearchTests {

        @Test
        void shouldSearchByName() {
            Product p = new Product();
            p.setName("Laptop Gamer");
            when(repository.findByNameContainingIgnoreCase("laptop"))
                    .thenReturn(List.of(p));
            List<Product> result = service.searchByName("laptop");
            assertThat(result).hasSize(1);
            assertThat(result.get(0).getName()).containsIgnoringCase("Laptop");
        }
    }
    
}

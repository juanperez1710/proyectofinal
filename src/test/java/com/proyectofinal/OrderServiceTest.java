package com.proyectofinal;

import com.proyectofinal.model.*;
import com.proyectofinal.model.Product;
import com.proyectofinal.repository.*;
import com.proyectofinal.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository orderRepo;
    @Mock
    ProductRepository productRepo;
    @Mock
    CustomerRepository customerRepo;

    @InjectMocks
    OrderService orderService;

    @Test
    void deberiaCrearPedidoYReducirStock() {
        Customer customer = new Customer();
        customer.setId(1L);
        when(customerRepo.findById(1L)).thenReturn(Optional.of(customer));

        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(20000.0);
        product.setStock(10);
        when(productRepo.findById(1L)).thenReturn(Optional.of(product));

        OrderItem item = new OrderItem();
        item.setProduct(product);
        item.setQuantity(2);

        Order order = orderService.createOrder(1L, List.of(item));

        assertThat(order.getTotal()).isEqualTo(40000.0);
        assertThat(product.getStock()).isEqualTo(8);
        verify(orderRepo).save(any(Order.class));
    }
}

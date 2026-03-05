package com.proyectofinal.dto;

import com.proyectofinal.model.Category;
import java.time.LocalDateTime;

public record ProductResponse(
        Long id,
        String name,
        Double price,
        String description,
        Integer stock,
        Category category,
        LocalDateTime createdAt
) {}

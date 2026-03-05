package com.proyectofinal.dto;

import com.proyectofinal.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
        @NotBlank(message = "El nombre es obligatorio")
        String name,

        @NotNull(message = "El precio es obligatorio")
        @Positive(message = "El precio debe ser mayor a cero")
        Double price,

        String description,

        @NotNull(message = "El stock es obligatorio")
        @Positive(message = "El stock debe ser igual o mayor a cero")
        Integer stock,

        @NotNull(message = "La categoría es obligatoria")
        Category category
) {}

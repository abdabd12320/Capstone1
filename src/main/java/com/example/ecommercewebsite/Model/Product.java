package com.example.ecommercewebsite.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

    @NotEmpty(message = "ID should not be empty")
    private String id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 4,message = "Name should be more than 3 characters")
    private String name;
    @NotNull(message = "Price should not be empty")
    @Positive(message = "Price should be positive numbers")
    private double price;
    @NotEmpty(message = "CategoryID should not be empty")
    private String categoryID;
}

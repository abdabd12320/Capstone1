package com.example.ecommercewebsite.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {

    @NotEmpty(message = "ID should not be empty")
    private String id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 4,message = "Name should be more than 3 characters")
    private String name;
}

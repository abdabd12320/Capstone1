package com.example.ecommercewebsite.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {

    @NotEmpty(message = "ID should not be empty")
    private String id;
    @NotEmpty(message = "productID should not be empty")
    private String productID;
    @NotEmpty(message = "MerchantID should not be empty")
    private String merchantID;
    @NotNull(message = "Stock should not be empty")
    @Min(value = 11,message = "Stock should be more than 10 numbers")
    private int stock;
}

package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite.ApiResponse.ApiResponseProduct;
import com.example.ecommercewebsite.Model.Product;
import com.example.ecommercewebsite.Service.ServiceProduct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ControllerProduct {

    private final ServiceProduct serviceProduct;

    @GetMapping("/get")
    public ResponseEntity getProducts()
    {
        if(serviceProduct.getProducts().isEmpty())
        {
            return ResponseEntity.status(200).body(new ApiResponseProduct("it is empty"));
        }
        return ResponseEntity.status(200).body(serviceProduct.getProducts());
    }
    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors errors)
    {
        if(errors.hasErrors())
        {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        serviceProduct.addProduct(product);
        return ResponseEntity.status(200).body(new ApiResponseProduct("Product added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable String id, @Valid@RequestBody Product product, Errors errors)
    {
        if(errors.hasErrors())
        {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(serviceProduct.updateProduct(id, product))
        {
            return ResponseEntity.status(200).body(new ApiResponseProduct("Product updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponseProduct("not found"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id)
    {
        if(serviceProduct.deleteProduct(id))
        {
            return ResponseEntity.status(200).body(new ApiResponseProduct("Product deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponseProduct("not found"));
    }
    @GetMapping("/search-by-product/{name}")
    public ResponseEntity searchByProduct(@PathVariable String name)
    {
        if(serviceProduct.searchByProduct(name)==null)
        {
            return ResponseEntity.status(400).body(new ApiResponseProduct("not found"));
        }
        return ResponseEntity.status(200).body(serviceProduct.searchByProduct(name));
    }
    @GetMapping("/get-less-price-sorted")
    public ResponseEntity getProductsLessPriceSorted()
    {
        return ResponseEntity.status(200).body(serviceProduct.getProductsLessPriceSorted());
    }
}

package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite.ApiResponse.ApiResponseCategory;
import com.example.ecommercewebsite.Model.Category;
import com.example.ecommercewebsite.Service.ServiceCategory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class ControllerCategory {

    private final ServiceCategory serviceCategory;

    @GetMapping("/get")
    public ResponseEntity getCategories()
    {
        if(serviceCategory.getCategories().isEmpty())
        {
            return ResponseEntity.status(200).body(new ApiResponseCategory("it is empty"));
        }
        return ResponseEntity.status(200).body(serviceCategory.getCategories());
    }
    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid@RequestBody Category category, Errors errors)
    {
        if(errors.hasErrors())
        {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        serviceCategory.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponseCategory("Category added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable String id, @Valid@RequestBody Category category,Errors errors)
    {
        if(errors.hasErrors())
        {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(serviceCategory.updateCategory(id, category))
        {
            return ResponseEntity.status(200).body(new ApiResponseCategory("Category updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponseCategory("not found"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable String id)
    {
        if(serviceCategory.deleteCategory(id))
        {
            return ResponseEntity.status(200).body(new ApiResponseCategory("Category deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponseCategory("not found"));
    }
}

package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite.ApiResponse.ApiResponseMerchant;
import com.example.ecommercewebsite.Model.Merchant;
import com.example.ecommercewebsite.Service.ServiceMerchant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class ControllerMerchant {

    private final ServiceMerchant serviceMerchant;

    @GetMapping("/get")
    public ResponseEntity getMerchants()
    {
        if(serviceMerchant.getMerchants().isEmpty())
        {
            return ResponseEntity.status(200).body(new ApiResponseMerchant("it is empty"));
        }
        return ResponseEntity.status(200).body(serviceMerchant.getMerchants());
    }
    @PostMapping("/add")
    public ResponseEntity addMerchant(@Valid @RequestBody Merchant merchant, Errors errors)
    {
        if(errors.hasErrors())
        {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        serviceMerchant.addMerchant(merchant);
        return ResponseEntity.status(200).body(new ApiResponseMerchant("Merchant added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable String id, @Valid@RequestBody Merchant merchant,Errors errors)
    {
        if(errors.hasErrors())
        {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(serviceMerchant.updateMerchant(id, merchant))
        {
            return ResponseEntity.status(200).body(new ApiResponseMerchant("Merchant updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponseMerchant("not found"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable String id)
    {
        if(serviceMerchant.deleteMerchant(id))
        {
            return ResponseEntity.status(200).body(new ApiResponseMerchant("Merchant deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponseMerchant("not found"));
    }
}

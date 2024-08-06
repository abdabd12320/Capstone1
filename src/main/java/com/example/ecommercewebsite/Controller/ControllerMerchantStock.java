package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite.ApiResponse.ApiResponseMerchantStock;
import com.example.ecommercewebsite.Model.MerchantStock;
import com.example.ecommercewebsite.Service.ServiceMerchantStock;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant-stock")
@RequiredArgsConstructor
public class ControllerMerchantStock {

    private final ServiceMerchantStock serviceMerchantStock;

    @GetMapping("/get")
    public ResponseEntity getMerchantsStock()
    {
        if(serviceMerchantStock.getMerchantStocks().isEmpty())
        {
            return ResponseEntity.status(200).body(new ApiResponseMerchantStock("it is empty"));
        }
        return ResponseEntity.status(200).body(serviceMerchantStock.getMerchantStocks());
    }
    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors)
    {
        if(errors.hasErrors())
        {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        serviceMerchantStock.addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body(new ApiResponseMerchantStock("MerchantStock added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable String id, @Valid@RequestBody MerchantStock merchantStock,Errors errors)
    {
        if(errors.hasErrors())
        {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(serviceMerchantStock.updateMerchantStock(id, merchantStock))
        {
            return ResponseEntity.status(200).body(new ApiResponseMerchantStock("MerchantStock updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponseMerchantStock("not found"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable String id)
    {
        if(serviceMerchantStock.deleteMerchantStock(id))
        {
            return ResponseEntity.status(200).body(new ApiResponseMerchantStock("MerchantStock deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponseMerchantStock("not found"));
    }
    @PutMapping("/change-stock/{stock}/{c}/{productid}/{merchantid}")
    public ResponseEntity changeStock(@PathVariable int stock, @PathVariable char c, @PathVariable String productid, @PathVariable String merchantid)
    {
        if(serviceMerchantStock.changeStock(stock, c, productid, merchantid))
        {
            return ResponseEntity.status(200).body(new ApiResponseMerchantStock("Stock added"));
        }
        return ResponseEntity.status(400).body(new ApiResponseMerchantStock("not found"));
    }
}

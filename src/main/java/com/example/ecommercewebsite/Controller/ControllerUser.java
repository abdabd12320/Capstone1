package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite.ApiResponse.ApiResponseUser;
import com.example.ecommercewebsite.Model.User;
import com.example.ecommercewebsite.Service.ServiceUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class ControllerUser {

    private final ServiceUser serviceUser;

    @GetMapping("/get")
    public ResponseEntity getUsers()
    {
        if(serviceUser.getUsers().isEmpty())
        {
            return ResponseEntity.status(200).body(new ApiResponseUser("it is empty"));
        }
        return ResponseEntity.status(200).body(serviceUser.getUsers());
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors)
    {
        if(errors.hasErrors())
        {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        serviceUser.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponseUser("User added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @Valid@RequestBody User user, Errors errors)
    {
        if(errors.hasErrors())
        {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(serviceUser.updateUser(id, user))
        {
            return ResponseEntity.status(200).body(new ApiResponseUser("User updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponseUser("not found"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id)
    {
        if(serviceUser.deleteUser(id))
        {
            return ResponseEntity.status(200).body(new ApiResponseUser("User deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponseUser("not found"));
    }
    @PutMapping("/buy-product/{userid}/{productid}/{merchantid}")
    public ResponseEntity buyProduct(@PathVariable String userid,@PathVariable String productid,@PathVariable String merchantid)
    {
        if (serviceUser.buyProduct(userid, productid, merchantid).equals("true"))
        {
            return ResponseEntity.status(200).body(new ApiResponseUser("Operation buy product successfully"));
        }
        if (serviceUser.buyProduct(userid, productid, merchantid).equals("false"))
        {
            return ResponseEntity.status(400).body(new ApiResponseUser("Bad request"));
        }
        return ResponseEntity.status(400).body(new ApiResponseUser("not found"));
    }
    @PutMapping("/product-return/{userid}/{productid}/{merchantid}")
    public ResponseEntity productReturn(@PathVariable String userid,@PathVariable String productid,@PathVariable String merchantid)
    {
        if (serviceUser.productReturn(userid, productid, merchantid))
        {
            return ResponseEntity.status(200).body(new ApiResponseUser("Operation return product successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponseUser("Bad request"));
    }
    @PutMapping("/change-price-by-merchant/{newprice}/{mid}/{pid}/{uid}")
    public ResponseEntity changePriceByMerchant(@PathVariable double newprice,@PathVariable String mid,@PathVariable String pid,@PathVariable String uid)
    {
        if(serviceUser.changePriceByMerchant(newprice, mid, pid, uid).equals("true"))
        {
            return ResponseEntity.status(200).body(new ApiResponseUser("The price changed"));
        }
        if(serviceUser.changePriceByMerchant(newprice, mid, pid, uid).equals("false"))
        {
            return ResponseEntity.status(400).body(new ApiResponseUser("Bad request"));
        }
        if(serviceUser.changePriceByMerchant(newprice, mid, pid, uid).equals("admin"))
        {
            return ResponseEntity.status(400).body(new ApiResponseUser("Only admin"));
        }
        return ResponseEntity.status(400).body(new ApiResponseUser("not found"));
    }
    @PutMapping("/discount-for-user-who-bought/{userid}/{merchantid}")
    public ResponseEntity discountForUserWhoBought(@PathVariable String userid,@PathVariable String merchantid)
    {
        if(serviceUser.discountForUserWhoBought(userid, merchantid))
        {
            return ResponseEntity.status(200).body(new ApiResponseUser("Done discount"));
        }
        return ResponseEntity.status(400).body(new ApiResponseUser("Bad request"));
    }
}

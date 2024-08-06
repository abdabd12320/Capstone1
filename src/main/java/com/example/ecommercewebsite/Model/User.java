package com.example.ecommercewebsite.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class User {

    @NotEmpty(message = "ID should not be empty")
    private String id;
    @NotEmpty(message = "Username should not be empty")
    @Size(min = 6,message = "Username should be more than 5 characters")
    private String username;
    @NotEmpty(message = "Password should not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9]{7,40}$",message = "Password must have digits and characters, also should be more than 6 length")
    private String password;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "The email must contain an @ sign")
    private String email;
    @NotEmpty(message = "Role should not be empty")
    @Pattern(regexp = "Admin|Customer")
    private String role;
    @NotNull(message = "Balance should not be empty")
    @Positive(message = "Balance should be positive")
    private double balance;
    @AssertFalse
    private boolean isBuy;
}

package com.legacy.system.dto;

// LEGACY: No proper imports organization
import java.util.*;
import java.math.BigDecimal;

// LEGACY: Mixed concerns in single DTO (user data + payment info)
public class MixedUseDTO {
    // LEGACY: Public fields instead of proper encapsulation
    public Long userId;
    public String userName;
    public String email;
    
    // LEGACY: Sensitive data mixed with regular user data
    public String creditCardNumber;
    public String cvv;
    public Date cardExpiry;
    
    // LEGACY: Using raw types
    public List addresses;
    public Map<String, Object> additionalData;
    
    // LEGACY: No proper constructor, using default
    
    // LEGACY: toString exposing sensitive data
    @Override
    public String toString() {
        return "MixedUseDTO{userId=" + userId + 
               ", userName='" + userName + 
               "', creditCardNumber='" + creditCardNumber + 
               "', cvv='" + cvv + "'}";
    }
} 
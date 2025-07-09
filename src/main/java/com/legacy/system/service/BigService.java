package com.legacy.system.service;

import com.legacy.system.dao.UserDao;
import com.legacy.system.dto.MixedUseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.text.SimpleDateFormat;

// LEGACY: God class with multiple responsibilities
@Service
public class BigService {
    
    // LEGACY: Direct field injection
    @Autowired
    private UserDao userDao;
    
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final Map<String, String> STATUS_MAP = new HashMap<>();
    
    // LEGACY: Static initializer in Spring bean
    static {
        STATUS_MAP.put("A", "ACTIVE");
        STATUS_MAP.put("I", "INACTIVE");
        STATUS_MAP.put("P", "PENDING");
    }
    
    // LEGACY: Long method with multiple responsibilities
    public Map<String, Object> processUserData(MixedUseDTO userDto, String action) {
        // LEGACY: Complex nested if-else logic
        if (action == null || action.trim().isEmpty()) {
            throw new RuntimeException("Invalid action");
        }
        
        Map<String, Object> result = new HashMap<>();
        
        // LEGACY: Business logic mixed with data access
        if (action.equals("CREATE")) {
            if (!userDao.check_user_exists(userDto.email)) {
                // LEGACY: Direct string manipulation of sensitive data
                String maskedCard = userDto.creditCardNumber.substring(
                    userDto.creditCardNumber.length() - 4);
                userDto.creditCardNumber = "XXXX-XXXX-XXXX-" + maskedCard;
                
                // LEGACY: Multiple responsibilities in single method
                transformData(userDto);
                userDao.saveUserData(userDto);
                result.put("status", "SUCCESS");
            } else {
                result.put("status", "DUPLICATE");
            }
        } else if (action.equals("READ")) {
            // LEGACY: Direct DAO access without abstraction
            Map<String, Object> userData = userDao.getUserById(userDto.userId.toString());
            if (userData != null) {
                // LEGACY: Business logic in service layer
                userData.put("status", STATUS_MAP.get(userData.get("status")));
                result.putAll(userData);
            }
        } else if (action.equals("LIST")) {
            List<Map<String, Object>> users = userDao.fetch_all_users();
            // LEGACY: Manual data transformation
            for (Map<String, Object> user : users) {
                user.put("formatted_date", 
                    new SimpleDateFormat(DATE_FORMAT).format(new Date()));
            }
            result.put("users", users);
        }

        // LEGACY: No proper validation or error handling
        return result;
    }
    
    // LEGACY: Private method with complex logic and no documentation
    private void transformData(MixedUseDTO dto) {
        // LEGACY: Nested if-else hell
        if (dto != null) {
            if (dto.userName != null) {
                if (dto.userName.length() > 0) {
                    dto.userName = dto.userName.toUpperCase();
                    if (dto.email != null) {
                        if (dto.email.contains("@")) {
                            String[] parts = dto.email.split("@");
                            if (parts.length == 2) {
                                if (parts[1].contains(".")) {
                                    // LEGACY: Unnecessary data manipulation
                                    dto.email = dto.email.toLowerCase();
                                } else {
                                    dto.email += ".com";
                                }
                            }
                        }
                    }
                }
            }
        }
        
        // LEGACY: Mixing concerns
        if (dto.additionalData == null) {
            dto.additionalData = new HashMap<>();
        }
        dto.additionalData.put("processedDate", new Date());
        dto.additionalData.put("processedBy", "SYSTEM");
    }
    
    // LEGACY: Utility methods in service class
    public static String formatDate(Date date) {
        return new SimpleDateFormat(DATE_FORMAT).format(date);
    }
} 
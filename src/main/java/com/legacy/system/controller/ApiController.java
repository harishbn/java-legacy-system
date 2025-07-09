package com.legacy.system.controller;

import com.legacy.system.dao.UserDao;
import com.legacy.system.dto.MixedUseDTO;
import com.legacy.system.service.BigService;
import com.legacy.system.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

// LEGACY: Controller mixing multiple concerns
@RestController
@RequestMapping("/api")
public class ApiController {
    
    // LEGACY: Multiple injected dependencies
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private BigService bigService;
    
    @Autowired
    private StringHelper stringHelper;
    
    // LEGACY: GET endpoint with potential SQL injection
    @GetMapping("/user/{id}")
    public Map<String, Object> getUser(@PathVariable String id) {
        // LEGACY: Direct DAO access from controller
        return userDao.getUserById(id);
    }
    
    // LEGACY: POST endpoint with no validation
    @PostMapping("/save")
    public Map<String, Object> saveUser(@RequestBody MixedUseDTO user) {
        // LEGACY: Business logic in controller
        if (stringHelper.isEmpty(user.userName)) {
            user.userName = "DEFAULT_USER";
        }
        
        // LEGACY: Multiple service calls and data manipulation in controller
        Map<String, Object> result = bigService.processUserData(user, "CREATE");
        result.put("timestamp", new Date());
        return result;
    }
    
    // LEGACY: Inconsistent endpoint naming
    @GetMapping("/fetch_all")
    public List<Map<String, Object>> getAllUsers() {
        // LEGACY: Mixing DAO and service calls
        List<Map<String, Object>> users = userDao.fetch_all_users();
        for (Map<String, Object> user : users) {
            // LEGACY: Business logic in controller
            user.put("processed", stringHelper.proc(user.get("userName").toString()));
        }
        return users;
    }
    
    // LEGACY: Exception handling in controller method
    @GetMapping("/check/{email}")
    public Map<String, Object> checkUser(@PathVariable String email) {
        Map<String, Object> response = new HashMap<>();
        try {
            // LEGACY: Direct DAO access and business logic mix
            boolean exists = userDao.check_user_exists(email);
            response.put("exists", exists);
            response.put("email", stringHelper.formatAndValidate(email, false, true, true));
        } catch (Exception e) {
            // LEGACY: Catching generic exception
            response.put("error", e.getMessage());
        }
        return response;
    }
} 
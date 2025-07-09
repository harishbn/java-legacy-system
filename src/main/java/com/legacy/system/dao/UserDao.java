package com.legacy.system.dao;

import com.legacy.system.dto.MixedUseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class UserDao {
    
    // LEGACY: Direct field injection
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    // LEGACY: Raw SQL with string concatenation (SQL injection risk)
    public Map<String, Object> getUserById(String id) {
        String sql = "SELECT * FROM users WHERE id = " + id;
        try {
            // LEGACY: Using raw Map instead of proper DTO
            return jdbcTemplate.queryForMap(sql);
        } catch (Exception e) {
            // LEGACY: Swallowing exception
            e.printStackTrace();
            return null;
        }
    }
    
    // LEGACY: Method name inconsistent with other methods
    public boolean check_user_exists(String email) {
        // LEGACY: SQL injection vulnerability
        String sql = "SELECT COUNT(*) FROM users WHERE email = '" + email + "'";
        try {
            int count = jdbcTemplate.queryForObject(sql, Integer.class);
            return count > 0;
        } catch (Exception e) {
            // LEGACY: Poor error handling
            System.out.println("Error checking user: " + e.getMessage());
            return false;
        }
    }
    
    // LEGACY: No transaction management
    public void saveUserData(MixedUseDTO dto) {
        // LEGACY: Hardcoded SQL with no prepared statement
        String sql = "INSERT INTO users (username, email, credit_card, cvv) VALUES ('" +
                    dto.userName + "', '" + dto.email + "', '" + 
                    dto.creditCardNumber + "', '" + dto.cvv + "')";
        
        // LEGACY: No proper error handling or rollback
        jdbcTemplate.execute(sql);
    }
    
    // LEGACY: Mixing different data access patterns
    public List<Map<String, Object>> fetch_all_users() {
        return jdbcTemplate.queryForList("SELECT * FROM users");
    }
} 
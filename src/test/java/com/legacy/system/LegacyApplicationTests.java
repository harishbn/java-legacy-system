package com.legacy.system;

import com.legacy.system.controller.ApiController;
import com.legacy.system.dto.MixedUseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// LEGACY: Using full Spring context for unit tests
@RunWith(SpringRunner.class)
@SpringBootTest
public class LegacyApplicationTests {
    
    // LEGACY: Direct autowiring in tests
    @Autowired
    private ApiController apiController;
    
    // LEGACY: No assertions, just prints
    @Test
    public void testGetUser() {
        System.out.println("Testing getUser...");
        System.out.println(apiController.getUser("1"));
    }
    
    // LEGACY: No test isolation
    @Test
    public void testSaveUser() {
        MixedUseDTO dto = new MixedUseDTO();
        dto.userName = "test";
        dto.email = "test@test.com";
        dto.creditCardNumber = "1234-5678-9012-3456";
        dto.cvv = "123";
        
        System.out.println("Testing saveUser...");
        System.out.println(apiController.saveUser(dto));
    }
    
    // LEGACY: Empty test method
    @Test
    public void contextLoads() {
    }
} 
package com.legacy.system.util;

import org.springframework.stereotype.Component;
import java.util.*;

// LEGACY: Static utility class marked as Spring component
@Component
public class StringHelper {
    
    // LEGACY: Reinventing Apache Commons StringUtils
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }
    
    // LEGACY: Non-thread-safe static field
    private static Map<String, String> cache = new HashMap<>();
    
    // LEGACY: Poorly named method
    public static String proc(String input) {
        if (cache.containsKey(input)) {
            return cache.get(input);
        }
        
        // LEGACY: Complex string manipulation without library use
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isUpperCase(c)) {
                result.append("_").append(Character.toLowerCase(c));
            } else {
                result.append(c);
            }
        }
        
        // LEGACY: Mutable static state
        cache.put(input, result.toString());
        return result.toString();
    }
    
    // LEGACY: Inconsistent parameter naming
    public static String concat(String s1, String str2) {
        // LEGACY: Inefficient string concatenation
        return s1 + "-" + str2;
    }
    
    // LEGACY: Method doing too many things
    public static String formatAndValidate(String text, boolean upperCase, 
                                         boolean trim, boolean checkEmail) {
        if (text == null) {
            return "";
        }
        
        String result = text;
        
        if (trim) {
            result = result.trim();
        }
        
        if (upperCase) {
            result = result.toUpperCase();
        }
        
        if (checkEmail) {
            // LEGACY: Poor email validation
            if (!result.contains("@")) {
                throw new RuntimeException("Invalid email: " + result);
            }
        }
        
        return result;
    }
    
    // LEGACY: Public constructor in utility class
    public StringHelper() {
        // LEGACY: Empty constructor
    }
} 
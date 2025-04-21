//Task 4
package com.hexawareassignment;

import java.security.SecureRandom;

public class PasswordGenerator {
    public static String generatePassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }

        return password.toString();
    }

    public static void main(String[] args) {
        String password = generatePassword(12);
        System.out.println("Generated Password: " + password);
    }
}


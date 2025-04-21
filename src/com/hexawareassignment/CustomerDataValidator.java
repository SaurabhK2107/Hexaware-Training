//Task 4
package com.hexawareassignment;

public class CustomerDataValidator {

    public static boolean validate(String data, String detail) {
        switch (detail.toLowerCase()) {
            case "name":
                return data.matches("[A-Z][a-zA-Z ]+");
            case "address":
                return data.matches("[A-Za-z0-9 ,.]+");
            case "phone":
                return data.matches("\\d{3}-\\d{3}-\\d{4}");
            default:
                return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(validate("John Doe", "name")); 
        System.out.println(validate("123 Main St.", "address")); 
        System.out.println(validate("123-456-7890", "phone")); 
    }
}

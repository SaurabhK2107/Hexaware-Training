//Task 4
package com.hexawareassignment;

public class OrderConfirmationEmail {

    public static void main(String[] args) {
        String customerName = "John Doe";
        String orderNumber = "BGT126";
        String address = "123 Main St, New York, NY";
        String deliveryDate = "2025-04-22";

        String message = generateConfirmation(customerName, orderNumber, address, deliveryDate);
        System.out.println(message);
    }

    public static String generateConfirmation(String name, String order, String address, String date) {
        return "Hello " + name + ",\n\n" +
               "Thank you for your order!\n" +
               "Order Number: " + order + "\n" +
               "Delivery Address: " + address + "\n" +
               "Expected Delivery Date: " + date + "\n\n" +
               "Best regards,\nCourier Company";
    }
}

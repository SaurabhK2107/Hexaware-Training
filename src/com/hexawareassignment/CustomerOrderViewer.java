package com.hexawareassignment;

public class CustomerOrderViewer {
    public static void main(String[] args) {
        String customerName = "John Doe"; // you can change this to test
        String[] senderNames = {"John Doe", "Jane Doe", "Bob Smith", "John Doe"};
        String[] trackingNumbers = {"BGT126", "DEF456", "GHI789", "JKL012"};

        System.out.println("Orders for customer: " + customerName);

        for (int i = 0; i < senderNames.length; i++) {
            if (senderNames[i].equalsIgnoreCase(customerName)) {
                System.out.println("Tracking Number: " + trackingNumbers[i]);
            }
        }
    }
}

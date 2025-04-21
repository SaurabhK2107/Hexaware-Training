package com.hexawareassignment;

public class OrderStatusChecker {
    public static void checkStatus(String status) {
        if (status.equalsIgnoreCase("delivered")) {
            System.out.println("Order has been delivered.");
        } else if (status.equalsIgnoreCase("processing")) {
            System.out.println("Order is still processing.");
        } else if (status.equalsIgnoreCase("cancelled")) {
            System.out.println("Order has been cancelled.");
        } else {
            System.out.println("Unknown order status.");
        }
    }

    public static void main(String[] args) {
        checkStatus("delivered");
    }
}

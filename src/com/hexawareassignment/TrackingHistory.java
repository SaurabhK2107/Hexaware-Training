package com.hexawareassignment;

public class TrackingHistory {
    public static void main(String[] args) {
        String[] trackingHistory = {
            "Dispatched from Kankavli",
            "Reached Mumbai Hub",
            "Left Mumbai Hub",
            "Out for delivery",
            "Delivered"
        };

        System.out.println("Tracking History:");
        for (String update : trackingHistory) {
            System.out.println(update);
        }
    }
}

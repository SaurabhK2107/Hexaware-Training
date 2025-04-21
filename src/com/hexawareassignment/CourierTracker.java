package com.hexawareassignment;

public class CourierTracker {
    public static void main(String[] args) throws InterruptedException {
        String[] locations = {
            "Warehouse", "On the way to hub", "At regional hub", 
            "Out for delivery", "Delivered"
        };

        int i = 0;
        while (i < locations.length) {
            System.out.println("Current Location: " + locations[i]);
            Thread.sleep(1000); 
            i++;
        }

        System.out.println("Courier has been successfully delivered.");
    }
}

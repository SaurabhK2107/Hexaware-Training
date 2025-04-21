//Task 4
package com.hexawareassignment;

public class ParcelStatusTracker {
    public static void main(String[] args) {
        String[][] parcelData = {
            {"BGT126", "In transit"},
            {"DEF456", "Out for delivery"},
            {"GHI789", "Delivered"}
        };

        String inputTrackingNumber = "GHI789"; // change to test

        for (String[] parcel : parcelData) {
            if (parcel[0].equalsIgnoreCase(inputTrackingNumber)) {
                System.out.println("Status for " + inputTrackingNumber + ": " + parcel[1]);
                return;
            }
        }

        System.out.println("Tracking number not found.");
    }
}

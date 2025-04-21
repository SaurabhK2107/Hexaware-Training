package com.hexawareassignment;

public class ParcelCategory {
    public static void categorize(double weight) {
        String category;

        if (weight < 2.0) {
            category = "light";
        } else if (weight <= 5.0) {
            category = "medium";
        } else {
            category = "heavy";
        }

        switch (category) {
            case "light":
                System.out.println("This is a light parcel.");
                break;
            case "medium":
                System.out.println("This is a medium parcel.");
                break;
            case "heavy":
                System.out.println("This is a heavy parcel.");
                break;
            default:
                System.out.println("Unknown parcel category.");
        }
    }

    public static void main(String[] args) {
        categorize(4.2);
    }
}

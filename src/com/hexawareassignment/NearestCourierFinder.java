package com.hexawareassignment;

public class NearestCourierFinder {
    public static void main(String[] args) {
        String[] couriers = {"Courier1", "Courier2", "Courier3"};
        int[] distances = {5, 2, 8}; // in km

        int minIndex = 0;

        for (int i = 1; i < distances.length; i++) {
            if (distances[i] < distances[minIndex]) {
                minIndex = i;
            }
        }

        System.out.println("Nearest available courier: " + couriers[minIndex] +
                           " (Distance: " + distances[minIndex] + " km)");
    }
}

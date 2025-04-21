package com.hexawareassignment;

public class CourierAssignment {
    public static void main(String[] args) {
        String[] couriers = {"Courier1", "Courier2", "Courier3"};
        int[] loads = {3, 1, 2}; 

        int index = 0;
        int minLoad = loads[0];

        for (int i = 1; i < loads.length; i++) {
            if (loads[i] < minLoad) {
                minLoad = loads[i];
                index = i;
            }
        }

        System.out.println("Assign courier to: " + couriers[index]);
    }
}

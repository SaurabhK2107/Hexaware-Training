//Task 4
package com.hexawareassignment;

public class ShippingCostCalculator {

    public static double calculateCost(double distance, double weight) {
        return 5.0 * distance + 10.0 * weight;
    }

    public static void main(String[] args) {
        double distance = 15.5; 
        double weight = 3.0;    
        double cost = calculateCost(distance, weight);

        System.out.println("Estimated Shipping Cost: ₹" + cost);
    }
}

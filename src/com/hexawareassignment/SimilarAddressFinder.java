//Task 4
package com.hexawareassignment;

public class SimilarAddressFinder {

    public static void main(String[] args) {
        String[] addresses = {
            "123 Main Street",
            "123 main st.",
            "456 Elm Street",
            "789 Main Avenue"
        };

        String keyword = "main";

        System.out.println("Addresses containing '" + keyword + "':");
        for (String address : addresses) {
            if (address.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(address);
            }
        }
    }
}

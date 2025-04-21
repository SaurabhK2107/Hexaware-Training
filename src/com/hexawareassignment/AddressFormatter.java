//Task 4
package com.hexawareassignment;

public class AddressFormatter {

    public static String formatAddress(String street, String city, String state, String zip) {
        String formatted = street + ", " + city + ", " + state + " - " + zip;
        return capitalizeEachWord(formatted);
    }

    private static String capitalizeEachWord(String input) {
        String[] words = input.toLowerCase().split(" ");
        StringBuilder formatted = new StringBuilder();

        for (String word : words) {
            if (word.length() > 0) {
                formatted.append(Character.toUpperCase(word.charAt(0)))
                         .append(word.substring(1))
                         .append(" ");
            }
        }
        return formatted.toString().trim();
    }

    public static void main(String[] args) {
        String result = formatAddress("123 main st", "new york", "ny", "10001");
        System.out.println(result);
    }
}

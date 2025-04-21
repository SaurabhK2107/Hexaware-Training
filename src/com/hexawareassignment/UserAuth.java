package com.hexawareassignment;

import java.util.Scanner;

public class UserAuth {
    public static void main(String[] args) {
        String savedEmail = "user@mail.com";
        String savedPassword = "1234";

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter email: ");
        String inputEmail = scanner.nextLine();

        System.out.print("Enter password: ");
        String inputPassword = scanner.nextLine();

        if (inputEmail.equals(savedEmail) && inputPassword.equals(savedPassword)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid email or password.");
        }

        scanner.close();
    }
}

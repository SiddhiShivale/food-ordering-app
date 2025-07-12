package com.aurionpro.admin;

import java.util.Scanner;

public class AdminCredentials {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "pass123";

    public static boolean login(Scanner scanner) {
        System.out.print("Enter admin username: ");
        String inputUser = scanner.nextLine();

        System.out.print("Enter password: ");
        String inputPass = scanner.nextLine();

        if (USERNAME.equals(inputUser) && PASSWORD.equals(inputPass)) {
            System.out.println("\nAdmin login successful.");
            return true;
        } else {
            System.out.println("\nInvalid credentials. Access denied.");
            return false;
        }
    }
}
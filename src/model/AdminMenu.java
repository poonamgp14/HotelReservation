package model;

import java.util.Scanner;

import static java.lang.System.in;

public class AdminMenu {
    public static String display() {
        Scanner scanner = new Scanner(in);
        System.out.println("-----------------------------------------------");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Add Test Data");
        System.out.println("6. Back to Main Menu");
        System.out.println("-----------------------------------------------");
        System.out.println("Please select a number for the menu option");
        return scanner.nextLine();
    }
}

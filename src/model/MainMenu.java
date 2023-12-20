package model;

import java.util.Scanner;

import static java.lang.System.in;

public class MainMenu {
    public static String display(){
        Scanner scanner = new Scanner(in);
        System.out.println("-----------------------------------------------");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("-----------------------------------------------");
        System.out.println("Please select a number for the menu option");
        return scanner.nextLine();
    }
}

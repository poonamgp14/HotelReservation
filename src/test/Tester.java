package test;

import model.Customer;

import java.util.Arrays;

public class Tester {
    public static void main(String[] args) {
        Customer customer = new Customer("firstname", "secondname", "j@domain.com");
        System.out.println(customer);
        Customer customer2 = new Customer("firstname", "secondname", "email");
        System.out.println(customer);

    }
}

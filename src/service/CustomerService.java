package service;

import model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerService {
    private static CustomerService customerServiceInstance;
    private static List<Customer> customers = new ArrayList<>();
    private CustomerService(){}

    static {
        try{
            customerServiceInstance = new CustomerService();
        }catch(Exception e){
            throw new RuntimeException("Exception occured in creating CustomerService singleton instance");
        }
    }

    public static void addCustomer(String email, String firstName, String lastName) {
        customers.add(new Customer(firstName,lastName,email ));

    }

    public static Customer getCustomer(String customerEmail){
        return customers.stream()
                .filter(customer -> customer.getEmail().equals(customerEmail))
                .findFirst()
                .orElse(null);
    }

    public static List<Customer> getAllCustomers(){
        return customers;
    }
}

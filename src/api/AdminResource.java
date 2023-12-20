package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;

public class AdminResource {
    private static AdminResource adminResourceInstance;

    private AdminResource(){}

    static {
        try{
            adminResourceInstance = new AdminResource();
        }catch(Exception e){
            throw new RuntimeException("Problem creating AdminResource singleton instance");
        }
    }

    public static AdminResource getInstance(){
        return adminResourceInstance;
    }

    public static Customer getCustomer(String email){
        return CustomerService.getCustomer(email);
    }

    public static void addRoom(IRoom room){
        ReservationService.addRoom(room);
    }

    public static Collection<IRoom> getAllRooms(){
        return ReservationService.getAllRooms();
    }

    public static Collection<Customer> getAllCustomers(){
        return CustomerService.getAllCustomers();
    }

    public static void displayAllReservations(){
        ReservationService.printAllReservation();
    }
}

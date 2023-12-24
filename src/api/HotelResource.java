package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static HotelResource hotelResourceInstance;

    private HotelResource(){};

    static {
     try{
      hotelResourceInstance = new HotelResource();
     }catch(Exception e){
      throw new RuntimeException("Problem creating HotelResource singleton instance");
     }
    }

    public static Customer getCustomer(String email){
     return CustomerService.getCustomer(email);
    }
     public static void createACustomer(String email, String firstName, String lastName){
      CustomerService.addCustomer(email,firstName,lastName);
     }

     public static IRoom getRoom(String roomNumber){
      return ReservationService.getARoom(roomNumber);
     }

     public static Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
      Customer customer = CustomerService.getCustomer(customerEmail);
      return ReservationService.reserveARoom(customer,room,checkInDate,checkOutDate);
     }

     public static Collection<Reservation> getCustomersReservations(String email){
        Customer customer = CustomerService.getCustomer(email);
        return ReservationService.getCustomersReservation(customer);
    }

     public static Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return ReservationService.findRooms(checkIn,checkOut);
     }
}

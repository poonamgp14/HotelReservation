package service;

import model.Customer;
import model.IRoom;
import model.Reservation;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationService {
    private static List<IRoom> rooms = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();;
    private static ReservationService reservationServiceInstance;

    private ReservationService(){}

    static {
        try{
            reservationServiceInstance = new ReservationService();
        }catch(Exception e){
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }

    public static ReservationService getInstance(){
        return reservationServiceInstance;
    }

    public static void addRoom(IRoom room){
        rooms.add(room);
    }
    public static List<IRoom> getAllRooms(){
        return rooms;
    }

    public static IRoom getARoom(String roomId){
        return rooms.stream()
                .filter(room -> room.getRoomNumber().equals(roomId))
                .findFirst()
                .orElse(null);
    }

    public static Reservation reserveARoom(Customer customer, IRoom room, Date CheckInDate, Date checkOutDate){
        Reservation res = new Reservation(customer,room,CheckInDate,checkOutDate);
        reservations.add(res);
        return res;
    }

    public static List<IRoom> findRooms(Date checkInDate,Date checkOutDate){
        return reservations.stream()
                .filter(r -> r.getCheckInDate().equals(checkInDate) && r.getCheckOutDate().equals(checkOutDate))
                .map(res -> res.getRoom())
                .collect(Collectors.toList());
    }

    public static List<Reservation> getCustomersReservation(Customer customer){
        return reservations.stream()
                .filter(reservation -> reservation.getCustomer().getEmail().equals(customer.getEmail()))
                .collect(Collectors.toList());

    }

    public static void printAllReservation(){
        System.out.println(reservations);
    }

}

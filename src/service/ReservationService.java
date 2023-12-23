package service;

import model.Customer;
import model.IRoom;
import model.Reservation;


import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReservationService {
    private static Set<IRoom> rooms = new HashSet<>();
    private static Set<Reservation> reservations = new HashSet<>();;
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
    public static Set<IRoom> getAllRooms(){
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
        List<IRoom> roomsBasedOnDate = reservations.stream()
                .filter(r -> r.getCheckInDate().after(checkOutDate) && r.getCheckOutDate().equals(checkInDate))
                .map(res -> res.getRoom())
                .collect(Collectors.toList());
        List<String> allReservedRooms = reservations.stream()
                .map(res -> res.getRoom().getRoomNumber())
                .collect(Collectors.toList());
        List<IRoom> freeRooms = new ArrayList<>(rooms).stream()
                .filter(room->allReservedRooms.contains(room.getRoomNumber()))
                .collect(Collectors.toList());
        return  Stream.concat(roomsBasedOnDate.stream(), freeRooms.stream()).toList();
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

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
    private static final int flexibleReservationDays = 7;

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
        System.out.println(roomId);
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
        List<IRoom> freeRooms = findFreeRooms();
        if(reservations.size()>0) {
            System.out.println("--------------");
            List<IRoom> roomsBasedOnDate = reservations.stream()
                    .filter(r -> r.getCheckOutDate().equals(checkInDate) || r.getCheckOutDate().before(checkInDate))
                    .map(res -> res.getRoom())
                    .collect(Collectors.toList());
            System.out.println(roomsBasedOnDate);
            return  Stream.concat(roomsBasedOnDate.stream(), freeRooms.stream()).toList();
//            while (roomsBasedOnDate.size() == 0) {
//                System.out.println("--------------i m in while-------");
//                System.out.println(roomsBasedOnDate);
//                long checkInDateLtime = checkInDate.getTime() + flexibleReservationDays * 24 * 60 * 60 * 1000;
//                checkInDate = new Date(checkInDateLtime);
//                System.out.println(checkInDate);
////                long checkOutDateLtime = checkOutDate.getTime() + flexibleReservationDays * 24 * 60 * 60 * 1000;
////                checkOutDate = new Date(checkOutDateLtime);
////                System.out.println(checkOutDate);
//                roomsBasedOnDate = findRoomsRecursive(checkInDate);
//            }
//            return  Stream.concat(roomsBasedOnDate.stream(), freeRooms.stream()).toList();
        }
        return freeRooms;

    }

    private static List<IRoom> findFreeRooms(){
        List<String> allReservedRooms = reservations.stream()
                .map(res -> res.getRoom().getRoomNumber())
                .collect(Collectors.toList());
        List<IRoom> freeRooms = new ArrayList<>(rooms).stream()
                .filter(room->!allReservedRooms.contains(room.getRoomNumber()))
                .collect(Collectors.toList());
        return freeRooms;
    }

    private static List<IRoom> findRoomsRecursive(Date checkInDate){
        return reservations.stream()
                    .filter(r -> r.getCheckOutDate().equals(checkInDate) || r.getCheckOutDate().before(checkInDate))
                    .map(res -> res.getRoom())
                    .collect(Collectors.toList());
    }
    public static Collection<Reservation> findPossibleReservation(Date checkIn, Date checkOut) {
        List<Reservation> roomsBasedOnDate = new ArrayList<>();
//        Date finalCheckIn = checkIn, finalCheckOut = checkOut;
        boolean continueToSearch = false;
        while (!continueToSearch) {
            System.out.println("--------------i m in while-------");
            long checkInDateLtime = checkIn.getTime() + flexibleReservationDays * 24 * 60 * 60 * 1000;
            checkIn = new Date(checkInDateLtime);
            long checkOutDateLtime = checkOut.getTime() + flexibleReservationDays * 24 * 60 * 60 * 1000;
            checkOut = new Date(checkOutDateLtime);
            Date finalCheckIn = checkIn;
            Date finalCheckOut = checkOut;
            System.out.println(finalCheckIn);
            System.out.println(finalCheckOut);
            List<IRoom> possibleRes = reservations.stream()
                    .filter(r ->  r.getCheckOutDate().before(finalCheckIn))
                    .map(r->r.getRoom())
                    .collect(Collectors.toList());
            possibleRes.forEach(room->{
                roomsBasedOnDate.add(new Reservation(null,room,finalCheckIn,finalCheckOut));
            });
            System.out.println(possibleRes);
            continueToSearch = true ? roomsBasedOnDate.size() > 0 : false;

            }
        return roomsBasedOnDate;

    }

    public static List<Reservation> getCustomersReservation(Customer customer){
        System.out.println("------------------Reservationservice getcustomer------");
        System.out.println(customer.getEmail());
        return reservations.stream()
                .filter(reservation -> reservation.getCustomer().getEmail().equals(customer.getEmail()))
                .collect(Collectors.toList());

    }

    public static void printAllReservation(){
        System.out.println(reservations);
    }


}

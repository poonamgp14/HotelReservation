import api.AdminResource;
import api.HotelResource;
import model.*;
import service.ReservationService;

import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.System.in;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        try {
            System.out.println("Welcome to the Hotel Reservation Application!");
            boolean isAppOpen = true;
            boolean displayMainMenu = true;
            String line;
            while (isAppOpen){
                line = displayMainMenu ? MainMenu.display() : AdminMenu.display();
                if (!displayMainMenu){
                    switch (line){
                        case "6":
                            displayMainMenu = true;
                            break;
                        case "4":
                            System.out.println("Enter room number");
                            String roomNumber = scanner.nextLine();
                            System.out.println("Enter price per night");
                            String roomPrice = scanner.nextLine();
                            System.out.println("Enter room type: 1 for single bed, 2 for double bed");
                            String rmType = scanner.nextLine();
                            AdminResource.addRoom(new Room(roomNumber,Double.parseDouble(roomPrice), rmType == "1" ? RoomType.SINGLE :  RoomType.DOUBLE));
                            System.out.println(ReservationService.getARoom(roomNumber));
                            break;
                        case "1":
                            Collection<Customer> customers = AdminResource.getAllCustomers();
                            System.out.println(customers);
                            break;
                        case "2":
                          Collection<IRoom> rooms = AdminResource.getAllRooms();
                          System.out.println(rooms);
                          break;
                        case "3":
                            AdminResource.displayAllReservations();
                            break;
                    }
                }
                if (displayMainMenu) {
                    switch (line) {
                        case "5":
                            scanner.close();
                            isAppOpen = false;
                            break;
                        case "1":
                            System.out.println("Enter checkIn date");
                            String checkIn = scanner.nextLine();
                            System.out.println("Enter checkOut date");
                            String checkOut = scanner.nextLine();
                            Date checkInDate = formatter.parse(checkIn);
                            Date checkOutDate = formatter.parse(checkOut);
                            Collection<IRoom> rooms = HotelResource.findARoom(checkInDate, checkOutDate);
                            System.out.println(rooms);
                            System.out.println("Do you want to Reserve a room?Print Y or N");
                            String ifReserve = scanner.nextLine();
                            if (ifReserve.equals('Y')) {
                                System.out.println("Enter your email Address");
                                String emailId = scanner.nextLine();
                                HotelResource.bookARoom(emailId, rooms.stream().findFirst().orElse(null), checkInDate, checkOutDate);
                            }
                            break;
                        case "3":
                            System.out.println("Enter your email Address");
                            String emailId = scanner.nextLine();
                            System.out.println("Enter your First Name");
                            String firstName = scanner.nextLine();
                            System.out.println("Enter your last Name");
                            String lastName = scanner.nextLine();
                            HotelResource.createACustomer(emailId,firstName,lastName);
                            System.out.println("Account is created. Proceed with reserving a room");
                            break;
                        case "2":
                            System.out.println("Enter your email Address");
                            String email = scanner.nextLine();
                            Collection<Reservation> reservations = HotelResource.getCustomersReservations(email);
                            System.out.println(reservations);
                            break;
                        case "4":
                            displayMainMenu = false;
                            break;


                    }
                }
            }
        }catch(Exception ex){
            System.out.println(ex.getLocalizedMessage());
        }finally {
            scanner.close();
        }
    }
}
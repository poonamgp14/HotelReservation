package model;

import java.util.Objects;

public class Room implements IRoom{
    private String roomNumber;
    private Double price;
    private RoomType roomType;
    private Integer id;

    public Room(String roomNumber,Double price, RoomType roomType) {
        this.price = price;
        this.roomType = roomType;
        this.roomNumber = roomNumber;
    }
    @Override
    public Integer getRoomId() {
        return id;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", roomType=" + roomType +
                ", id=" + id +
                '}';
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomNumber.equals(room.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
}

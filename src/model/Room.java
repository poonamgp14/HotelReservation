package model;

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
}

package model;

public class FreeRoom extends Room{
    public FreeRoom(String roomNumber, RoomType roomType) {
        super(roomNumber,0.0, roomType);
    }

    @Override
    public boolean isFree() {
        return true;
    }

    @Override
    public String toString() {
        return "FreeRoom{" +
                "roomNumber='" + getRoomNumber() + '\'' +
                ", price=" + 0.0 +
                ", roomType=" + getRoomType() +
                '}';
    }
}

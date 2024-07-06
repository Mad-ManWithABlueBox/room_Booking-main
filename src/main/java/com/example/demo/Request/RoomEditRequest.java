package com.example.demo.Request;

public class RoomEditRequest {
    private int roomID;
    private String roomName;
    private int roomCapacity;

    // Getters and setters
    public Integer getRoomID() {
        return roomID;
    }

    public String getRoomName() {
        return roomName;
    }



    public int getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }
}

package com.example.demo.Request;

import lombok.Setter;

@Setter
public class RoomAddRequest {
    private String roomName;
    private int roomCapacity;

    // Getters and setters
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }
}

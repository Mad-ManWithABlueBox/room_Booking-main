package com.example.demo.Response;

public class AllRoomResponse {
    private int roomID;
    private String roomName;
    private int roomCapacity;

    public AllRoomResponse(int roomID, String roomName, int roomCapacity) {
        this.roomID= roomID;
        this.roomName = roomName;
        this.roomCapacity = roomCapacity;
    }


    public String getRoomName() {
        return roomName;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public int getroomID() {
        return roomID;
    }


}

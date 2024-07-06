package com.example.demo.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TempRoomResponse {
    @JsonProperty("roomID")
    private int roomID;

    @JsonProperty("roomName")
    private String roomName;

    @JsonProperty("roomCapacity")
    private int roomCapacity;

    @JsonProperty("booked")
    List<TempDetailsBooking> booked;

    public TempRoomResponse(int roomID, String roomName, int roomCapacity, List<TempDetailsBooking> booked) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.roomCapacity = roomCapacity;
        this.booked = booked;
    }




}

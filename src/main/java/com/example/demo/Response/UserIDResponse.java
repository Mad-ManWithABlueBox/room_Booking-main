package com.example.demo.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserIDResponse {
    @JsonProperty("userID")
    private int userID;

    public UserIDResponse(int userID) {
        this.userID = userID;
    }
}

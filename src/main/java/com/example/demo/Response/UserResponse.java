package com.example.demo.Response;

public class UserResponse {
    private int userID;
    private String name;
    private String email;

    // Constructor
    public UserResponse() {
        // Default constructor
    }

    public UserResponse(int userID, String name, String email) {
        this.userID = userID;
        this.name = name;
        this.email = email;
    }

    // Getters and setters
    public int getuserID() {
        return userID;
    }

    public void setuserID(Long userId) {
        this.userID= userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

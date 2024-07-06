package com.example.demo.Response;

public class AllUserResponse {
    private int userID;
    private String name;
    private String email;

    public AllUserResponse(Integer userID, String name, String email) {
        this.userID= userID;
        this.name = name;
        this.email = email;
    }

    public AllUserResponse() {
        
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getuserID() {
        return userID;
    }

}

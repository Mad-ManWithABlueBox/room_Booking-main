package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Response.Errororor;
import com.example.demo.Response.UserResponse;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDetailController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<?> getUserDetails(@RequestParam("userID") int userID) {
        User user = userService.getUserByuserID(userID);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Errororor("User does not exist"));
        } else {
            UserResponse userResponse = new UserResponse(userID, user.getName(), user.getEmail());
            return ResponseEntity.ok(userResponse);
        }
    }
}

package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Response.AllUserResponse;
import com.example.demo.Response.UserResponse;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class AllUsersController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<AllUserResponse> users = userService.getAllUsers();
        List<AllUserResponse> userResponses = users.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userResponses);
    }

    private AllUserResponse convertToResponse(AllUserResponse user) {
        return new AllUserResponse(user.getuserID(),user.getName(),user.getEmail());
    }
}

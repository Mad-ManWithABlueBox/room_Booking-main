package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Response.Errororor;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SignupController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        String email = signupRequest.getEmail();

        // Check if user with provided email already exists
        if (userService.getUserByEmail(email) != null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body((new Errororor("Forbidden, Account already exists")));
        }

        // Create new user
        User newUser = new User(signupRequest.getEmail(), signupRequest.getName(), signupRequest.getPassword());
        userService.createUser(newUser);

        return ResponseEntity.ok("Account Creation Successful");
    }

    // Request body class
    static class SignupRequest {
        private String email;
        private String name;
        private String password;

        // getters and setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}

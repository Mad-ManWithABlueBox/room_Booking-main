package com.example.demo.Controller;

import com.example.demo.Model.Room;
import com.example.demo.Repository.BookingRepository;
import com.example.demo.Repository.RoomRepository;
import com.example.demo.Request.DeleteRequest;
import com.example.demo.Request.RoomAddRequest;
import com.example.demo.Request.RoomEditRequest;
import com.example.demo.Response.Errororor;
import com.example.demo.Response.TempRoomResponse;
import com.example.demo.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @PostMapping
    public ResponseEntity<?> addRoom(@RequestBody RoomAddRequest roomRequest) {

        // Check if room already exists
        if (roomService.isRoomExistsName(roomRequest.getRoomName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Errororor("Room already exists"));
        }

        // Validate room capacity
        if (roomRequest.getRoomCapacity() < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Errororor("Invalid capacity"));
        }


        // Create the room
        roomService.createRoom(roomRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Room created successfully");
    }
    @PatchMapping
    public ResponseEntity<?> editRoom(@RequestBody RoomEditRequest roomEditRequest) {

        if (!roomService.isRoomExists(roomEditRequest.getRoomID())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Errororor("Room does not exist"));
        }

        if (roomRepository.existsByRoomName(roomEditRequest.getRoomName())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Errororor("Room with given name already exists"));
        }
        // Validate room capacity
        if (roomEditRequest.getRoomCapacity() < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Errororor("Invalid capacity"));
        }
        // Edit the room
        roomService.editRoom(roomEditRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Room edited successfully");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteRoom(@RequestBody DeleteRequest deleteRequest) {
        int roomID = deleteRequest.getRoomID();

        // Check if room exists
        if (!roomService.isRoomExists(roomID)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Errororor("Room does not exist"));
        }

        // Delete the room
        roomService.deleteRoom(roomID);
        return ResponseEntity.status(HttpStatus.OK).body("Room deleted successfully");
    }
    @GetMapping
    public ResponseEntity<?> filterRoomsByCapacity(@RequestParam(value = "capacity", required = false, defaultValue = "0") int capacity) {
        // Check if the provided capacity is valid
        if (capacity < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Errororor("Invalid parameters"));
        }

        // Filter rooms based on capacity
        List<Room> roomss = roomRepository.findByRoomCapacityGreaterThanEqual(capacity);
        List<TempRoomResponse> roomResponses =  roomService.roomToTemp(roomss, bookingRepository);



        // Return the filtered rooms in the response body
        return ResponseEntity.ok(roomResponses);
    }
    public void reverseString(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
    }

    public void swapNumbers(int a, int b) {
        a = a + b;
        b = a - b;
        a = a - b;
    }

    public void calculateFactorial(int num) {
        int fact = 1;
        for (int i = 1; i <= num; i++) {
            fact *= i;
        }
    }

    public void checkPalindrome(String str) {
        StringBuilder sb = new StringBuilder(str);
        String reverse = sb.reverse().toString();
        if (str.equals(reverse)) {
            // Palindrome
        } else {
            // Not a Palindrome
        }
    }

    public void findLargestNumber(int[] arr) {
        int largest = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > largest) {
                largest = arr[i];
            }
        }
    }

    public void convertToUpperCase(StringBuilder sb) {
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (Character.isLowerCase(c)) {
                sb.setCharAt(i, Character.toUpperCase(c));
            }
        }
    }

    public void countVowelsAndConsonants(String str) {
        int vowelCount = 0;
        int consonantCount = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isLetter(c)) {
                if (isVowel(c)) {
                    vowelCount++;
                } else {
                    consonantCount++;
                }
            }
        }
    }

    private boolean isVowel(char c) {
        c = Character.toUpperCase(c);
        return (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U');
    }

}

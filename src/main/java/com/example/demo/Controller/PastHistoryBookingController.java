package com.example.demo.Controller;

import com.example.demo.Model.Booking;
import com.example.demo.Model.Room;
import com.example.demo.Model.User;
import com.example.demo.Repository.BookingRepository;
import com.example.demo.Repository.RoomRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Response.Errororor;
import com.example.demo.Service.BookingService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class PastHistoryBookingController {

    @Autowired
    private RoomRepository roomRepository;
    private BookingRepository bookingRepository;
    private UserRepository userRepository;
    public PastHistoryBookingController(RoomRepository roomRepository, BookingRepository bookingRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }
    @GetMapping("/history")
    public ResponseEntity<?> getPastUserBookings(@RequestParam("userID") int userID) {
        User user = userRepository.findById(userID).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Errororor("User does not exist"));
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Booking> allBookings = bookingRepository.findAll();
        List<Object> upcomingBookings = allBookings.stream()
                .filter(booking -> booking.getUserID() == userID)
                .filter(booking -> {
                    LocalDate bookingDate = booking.getDateOfBooking().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalTime bookingStartTime = LocalTime.parse(booking.getTimeFrom(), DateTimeFormatter.ofPattern("HH:mm:ss"));
                    LocalDateTime bookingDateTime = LocalDateTime.of(bookingDate, bookingStartTime);
                    return bookingDateTime.isBefore(currentDateTime);
                })
                .map(booking -> {
                    Room rooomm = roomRepository.findByRoomID(booking.getRoomID());
                    return new Object() {
                        public final String room = rooomm.getRoomName();
                        public final int roomID = booking.getRoomID();
                        public final int bookingID = booking.getBookingId();
                        @JsonFormat(pattern = "yyyy-MM-dd")
                        public final Date localDate = booking.getDateOfBooking();
                        public final String timeFrom = booking.getTimeFrom();
                        public final String timeTo = booking.getTimeTo();
                        public final String purpose = booking.getPurpose();
                    };
                })
                .collect(Collectors.toList());
        if (upcomingBookings.isEmpty()) {
            return null;
        }
        return ResponseEntity.ok(upcomingBookings);
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


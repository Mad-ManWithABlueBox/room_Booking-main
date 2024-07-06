package com.example.demo.Controller;

import com.example.demo.Request.BookingEditRequest;
import com.example.demo.Response.Errororor;
import com.example.demo.Service.BookingService;
import com.example.demo.Request.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @PatchMapping("/book")
    public ResponseEntity<?> editBooking(@RequestBody BookingEditRequest bookingEditRequest) {
        ResponseEntity<?> response = bookingService.editBooking(bookingEditRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
    @PostMapping("/book")
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest bookingRequest) {

        return bookingService.createBooking(bookingRequest);
    }

//    private boolean isValidDateTimeFormat(Date date, String timeFrom, String timeTo) {
//        // Check if the provided time matches the expected format
//        String timeRegex = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$";
//        if (!timeFrom.matches(timeRegex) || !timeTo.matches(timeRegex)) {
//            return false;
//        }
//
//        try {
//            // Attempt to parse the date, if it throws an exception, the date is invalid
//            LocalDate.parse(date.toString());
//        } catch (DateTimeParseException e) {
//            return false;
//        }
//
//        return true;
//    }

    @DeleteMapping("/book")
    public ResponseEntity<?> deleteBooking(@RequestParam("bookingID") int bookingID) {
        return bookingService.deleteBooking(bookingID);
    }
}

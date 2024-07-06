package com.example.demo.Service;

import com.example.demo.Model.Booking;
import com.example.demo.Repository.BookingRepository;
import com.example.demo.Repository.RoomRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Request.BookingEditRequest;
import com.example.demo.Request.BookingRequest;
import com.example.demo.Response.Errororor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public BookingService(UserRepository userRepository, RoomRepository roomRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    public ResponseEntity<?> createBooking(BookingRequest bookingRequest) {
        // Check if the user exists
        if (!userExists(bookingRequest.getUserID())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Errororor("User does not exist"));
        }
        // Check if the room exists
        if (!roomExists(bookingRequest.getRoomID())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Errororor("Room does not exist"));
        }
//        if (!isValidDateTime(bookingRequest.getDateOfBooking(), bookingRequest.getTimeFrom(), bookingRequest.getTimeTo())) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Errororor("Invalid date/time"));
//        }


//         Validate date and time


        // Check room availability
        if (!isRoomAvailable(bookingRequest.getRoomID(), bookingRequest.getDateOfBooking(), bookingRequest.getTimeFrom(), bookingRequest.getTimeTo())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Errororor("Room unavailable"));
        }

        if (!isValidDateTime(bookingRequest.getDateOfBooking(), bookingRequest.getTimeFrom(), bookingRequest.getTimeTo())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Errororor("Invalid date/time"));
        }

        // Create Booking object
        Booking booking = new Booking();
        booking.setUserID(bookingRequest.getUserID());
        booking.setRoomID(bookingRequest.getRoomID());
        booking.setDateOfBooking(bookingRequest.getDateOfBooking());
        booking.setTimeFrom(bookingRequest.getTimeFrom());
        booking.setTimeTo(bookingRequest.getTimeTo());
        booking.setPurpose(bookingRequest.getPurpose());

        // Save booking
        bookingRepository.save(booking);

        return ResponseEntity.status(HttpStatus.CREATED).body("Booking created successfully");
    }

    public ResponseEntity<?> deleteBooking(int bookingID) {
        // Check if the booking exists
        if (!bookingRepository.existsById(bookingID) ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Errororor("Booking does not exist"));
        }


        // Delete the booking
        bookingRepository.deleteById(bookingID);

        return ResponseEntity.status(HttpStatus.OK).body("Booking deleted successfully");
    }

    public ResponseEntity<?> editBooking(BookingEditRequest bookingEditRequest) {

        // Check if the user exists
        if (!userExists(bookingEditRequest.getUserID())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Errororor("User does not exist"));
        }
        // Check if the room exists
        if (!roomExists(bookingEditRequest.getRoomID())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Errororor("Room does not exist"));
        }



        // Check if the booking exists
        if (!bookingRepository.existsById(bookingEditRequest.getBookingID())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Errororor("Booking does not exist"));
        }

        // Check room availability
        if (!isRoomAvailable(bookingEditRequest.getRoomID(), bookingEditRequest.getDateOfBooking(), bookingEditRequest.getTimeFrom(), bookingEditRequest.getTimeTo())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Errororor("Room unavailable"));
        }

        if (!isValidDateTime(bookingEditRequest.getDateOfBooking(), bookingEditRequest.getTimeFrom(), bookingEditRequest.getTimeTo())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Errororor("Invalid date/time"));
        }



        // Update Booking object
        Booking booking = bookingRepository.findById(bookingEditRequest.getBookingID()).orElse(null);
        if (booking != null) {
            booking.setUserID(bookingEditRequest.getUserID());
            booking.setRoomID(bookingEditRequest.getRoomID());
            booking.setDateOfBooking(bookingEditRequest.getDateOfBooking());
            booking.setTimeFrom(bookingEditRequest.getTimeFrom());
            booking.setTimeTo(bookingEditRequest.getTimeTo());
            booking.setPurpose(bookingEditRequest.getPurpose());

            // Save updated booking
            bookingRepository.save(booking);

            return ResponseEntity.status(HttpStatus.OK).body("Booking modified successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Errororor("Booking does not exist"));
        }
    }

    // Method to check if the room exists
    private boolean roomExists(int roomID) {
        return roomRepository.existsById(roomID);
    }

    // Method to check if the user exists
    private boolean userExists(int userID) {
        return userRepository.existsById(userID);
    }


    // Method to validate date and time
    private boolean isValidDateTime(Date date, String timeFrom, String timeTo) {
        // Check if date is in the past
        Date currentDate = new Date();
        if (date.before(currentDate)) {
            return false;
        }

        // Check if timeFrom is before timeTo
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try {
            Date startTime = sdf.parse(timeFrom);
            Date endTime = sdf.parse(timeTo);
            if (startTime.after(endTime)) {
                return false;
            }
        } catch (ParseException e) {
            return false; // Invalid time format
        }

        return true;
    }

    // Method to check room availability
    private boolean isRoomAvailable(int roomId, Date date, String timeFrom, String timeTo) {
        // Parse time strings to Date objects
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date startTime, endTime;
        try {
            startTime = sdf.parse(timeFrom);
            endTime = sdf.parse(timeTo);
        } catch (ParseException e) {
            // Invalid time format
            return false;
        }

        // Get existing bookings for the specified room and date
        List<Booking> bookings = bookingRepository.findByRoomIDAndDateOfBooking(roomId, date);

        // Check if there are any bookings for the specified time range
        for (Booking booking : bookings) {
            // Parse booking start and end times
            Date bookingStartTime, bookingEndTime;
            try {
                bookingStartTime = sdf.parse(booking.getTimeFrom());
                bookingEndTime = sdf.parse(booking.getTimeTo());
            } catch (ParseException e) {
                // Invalid time format in existing booking
                return false;
            }

            // Check for overlapping time ranges
            if (startTime.before(bookingEndTime) && endTime.after(bookingStartTime)) {
                // There is a booking that overlaps with the specified time range
                return false;
            }
        }

        // No overlapping bookings found, room is available
        return true;
    }

    public List<Booking> getBookingHistory(int userID) {
        return bookingRepository.findByUserID(userID);
    }

    public List<Booking> getUpcomingBookings(int userID) {
        Date currentDate = new Date();

        // Retrieve upcoming bookings for the specified user
        return bookingRepository.findByUserIDAndDateOfBookingGreaterThanEqualOrderByDateOfBookingAsc(userID, currentDate);
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

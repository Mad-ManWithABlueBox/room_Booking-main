package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    private int userID;
    private int roomID;
    private Date dateOfBooking;
    private String timeFrom;
    private String timeTo;
    private String purpose;

    // Constructor, getters, and setters


    public Booking(int userID, int roomID, Date dateOfBooking, String timeFrom, String timeTo, String purpose) {
        this.userID = userID;
        this.roomID = roomID;
        this.dateOfBooking = dateOfBooking;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.purpose = purpose;
    }

    public Booking() {

    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

        public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public void setDateOfBooking(Date dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
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

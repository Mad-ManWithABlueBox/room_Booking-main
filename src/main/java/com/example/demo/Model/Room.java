package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomID;

    private String roomName;
    private int roomCapacity;

    // Constructors, getters, and setters


    public Room(String roomName, int roomCapacity) {
        this.roomName = roomName;
        this.roomCapacity = roomCapacity;
    }

    public Room() {

    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
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

package com.example.demo.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class TempDetailsBooking {
    @JsonProperty("bookingID")
    private int bookingID;
    @JsonProperty("bookingDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bookingDate;
    @JsonProperty("timeFrom")
    private String timeFrom;
    @JsonProperty("timeTo")
    private String timeTo;
    @JsonProperty("purpose")
    private String purpose;
    @JsonProperty("user")
    private UserIDResponse userID;

    public TempDetailsBooking(int bookingID, UserIDResponse userID, Date bookingDate, String timeFrom, String timeTo, String purpose ) {
        this.bookingID = bookingID;
        this.bookingDate = bookingDate;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.purpose = purpose;
        this.userID = userID;
    }
}

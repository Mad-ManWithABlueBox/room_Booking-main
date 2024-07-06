package com.example.demo.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Errororor {
    @JsonProperty("Error")
    private String Error;

    public Errororor(String Error) {
        this.Error = Error;
    }

}

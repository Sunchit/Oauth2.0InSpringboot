package com.example.springbootchallenge.rest.dto;

import java.io.Serializable;

public class TicketBookingRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String movieName;
    private String userName;
    private int numberOfTickets;

    public  TicketBookingRequest(){

    }

    // Constructors, Getters, and Setters
    public TicketBookingRequest(String movieName, String userName, int numberOfTickets) {
        this.movieName = movieName;
        this.userName = userName;
        this.numberOfTickets = numberOfTickets;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getUserName() {
        return userName;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }
}

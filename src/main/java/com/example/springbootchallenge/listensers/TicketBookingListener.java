package com.example.springbootchallenge.listensers;

import com.example.springbootchallenge.rest.dto.TicketBookingRequest;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TicketBookingListener {

    @JmsListener(destination = "ticket.queue")
    public void receiveBookingRequest(TicketBookingRequest bookingRequest) {
        // Process the ticket booking (seat allocation, sending emails, etc.)
        System.out.println("Processing ticket booking for movie: " + bookingRequest.getMovieName());
        System.out.println("User: " + bookingRequest.getUserName() + " requested "
                + bookingRequest.getNumberOfTickets() + " tickets.");
        // Add logic to confirm booking, allocate seats, etc.
    }

}


package com.example.springbootchallenge.service.Impl;

import com.example.springbootchallenge.rest.dto.TicketBookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class TicketBookingService  {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void bookTicket(TicketBookingRequest bookingRequest) {
        // Send the booking request to the queue
        jmsTemplate.convertAndSend("ticket.queue", bookingRequest);
        System.out.println("Ticket booking request sent for movie: " + bookingRequest.getMovieName());
    }
}

package com.example.springbootchallenge.rest;

import com.example.springbootchallenge.rest.dto.TicketBookingRequest;
import com.example.springbootchallenge.service.Impl.TicketBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class TicketBookingController {

    @Autowired
    private TicketBookingService bookingService;

    @PostMapping("/ticket")
    public String bookTicket(@RequestBody TicketBookingRequest bookingRequest) {
        bookingService.bookTicket(bookingRequest);
        return "Booking request received for " + bookingRequest.getMovieName();
    }
}
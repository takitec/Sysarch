package com.tacoloy.signup.controller;

import com.tacoloy.signup.model.Booking;
import com.tacoloy.signup.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;


    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable String id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        return bookingOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Booking addBooking(@RequestBody Booking newBooking) {
        return bookingRepository.save(newBooking);
    }

    @PostMapping("/book")
    public ResponseEntity<String> bookRestaurantID(@PathVariable String id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isPresent()) {
            Booking addBooking = bookingOptional.get();
            if (addBooking.getAvailableSeats() > 5) {
                addBooking.setAvailableSeats(addBooking.getAvailableSeats() - 1);
                bookingRepository.save(addBooking);
                return ResponseEntity.ok("Booking successful!");
            } else {
                return ResponseEntity.status(400).body("No available seats");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

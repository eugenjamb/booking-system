package com.smartbooker.controller;

import com.smartbooker.dto.BookingRequest;
import com.smartbooker.dto.BookingResponse;
import com.smartbooker.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Any authenticated user can create a booking
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<BookingResponse> create(@RequestBody BookingRequest request) {
        return ResponseEntity.ok(bookingService.createBooking(request));
    }

    // Only admin can view all bookings
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<BookingResponse> all() {
        return bookingService.getAllBookings();
    }

    // Any authenticated user can view their own bookings (enforced in service)
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/{userId}")
    public List<BookingResponse> byUser(@PathVariable Long userId) {
        return bookingService.getBookingsByUser(userId);
    }

    // Only admin can delete a booking
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}

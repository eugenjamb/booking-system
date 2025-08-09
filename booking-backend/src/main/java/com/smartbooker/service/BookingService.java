package com.smartbooker.service;

import com.smartbooker.dto.BookingRequest;
import com.smartbooker.dto.BookingResponse;
import com.smartbooker.model.Booking;
import com.smartbooker.model.Role;
import com.smartbooker.model.User;
import com.smartbooker.repository.BookingRepository;
import com.smartbooker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private UserRepository userRepo;

    public BookingResponse createBooking(BookingRequest request) {
        // Get currently authenticated user
        User currentUser = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        User bookingUser;

        if (currentUser.getRole() == Role.ADMIN) {
            // Admin can book for anyone
            bookingUser = userRepo.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
        } else {
            // Normal user: must only book for themselves
            if (!request.getUserId().equals(currentUser.getId())) {
                throw new RuntimeException("You are not allowed to create a booking for another user.");
            }
            bookingUser = currentUser;
        }

        Booking booking = new Booking();
        booking.setDate(request.getDate());
        booking.setTime(request.getTime());
        booking.setServiceType(request.getServiceType());
        booking.setUser(bookingUser);
        booking.setConfirmed(false);

        booking = bookingRepo.save(booking);

        return toResponse(booking);
    }

    public List<BookingResponse> getAllBookings() {
        return bookingRepo.findAll().stream().map(this::toResponse).toList();
    }

    public List<BookingResponse> getBookingsByUser(Long userId) {
        return bookingRepo.findByUserId(userId).stream().map(this::toResponse).toList();
    }

    public void deleteBooking(Long id) {
        bookingRepo.deleteById(id);
    }

    private BookingResponse toResponse(Booking booking) {
        BookingResponse res = new BookingResponse();
        res.setId(booking.getId());
        res.setDate(booking.getDate());
        res.setTime(booking.getTime());
        res.setServiceType(booking.getServiceType());
        res.setUserEmail(booking.getUser().getEmail());
        res.setConfirmed(booking.isConfirmed());
        return res;
    }
}

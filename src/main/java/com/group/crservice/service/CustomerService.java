package com.group.crservice.service;

import com.group.crservice.request.BookingRequest;
import com.group.crservice.response.BookingResponse;

import java.util.List;

public interface CustomerService {

    BookingResponse bookVehicle(BookingRequest request);

    String cancelBooking(long bookingId);

    String approveBooking(long bookingId);

    List<BookingResponse> getAllBookingRequests();

    List<BookingResponse> getAllPending();

    long getAllBookings();

    long getAllApprovedBookings();
}

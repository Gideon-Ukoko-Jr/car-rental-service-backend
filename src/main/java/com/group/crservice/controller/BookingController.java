package com.group.crservice.controller;

import com.group.crservice.request.AddVehicleRequest;
import com.group.crservice.request.BookingRequest;
import com.group.crservice.response.BookingResponse;
import com.group.crservice.response.VehicleResponse;
import com.group.crservice.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
@Api(tags = "Vehicle Endpoints", description = "Handles Booking Endpoints and Management")
@AllArgsConstructor
public class BookingController {

    private final CustomerService customerService;

    @PostMapping("/book-vehicle")
    @ApiOperation(value = "Book Vehicle")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<BookingResponse> bookVehicle(@RequestBody BookingRequest request) {
//        if (request.getVehicleId() <= 10){
//            BookingResponse bookingResponse = customerService.bookVehicle("11");
//        }
        BookingResponse bookingResponse = customerService.bookVehicle(request);
        return new ResponseEntity<>(bookingResponse, HttpStatus.CREATED);
    }

    @PutMapping("/cancel-booking/{id}")
    @ApiOperation(value = "Cancel Vehicle Booking")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id){
        String response = customerService.cancelBooking(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/approve-booking/{id}")
    @ApiOperation(value = "Approve Booking")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<String> approveBooking(@PathVariable Long id){
        String response = customerService.approveBooking(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/requests")
    @ApiOperation(value = "Get All Booking Requests")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<List<BookingResponse>> getAllBookingRequests(){
        List<BookingResponse> response = customerService.getAllBookingRequests();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/requests/pending")
    @ApiOperation(value = "Get All Pending Booking Requests")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<List<BookingResponse>> getAllPendingBookingRequests(){
        List<BookingResponse> response = customerService.getAllPending();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/requests/count")
    @ApiOperation(value = "Get Number of Pending Booking Requests")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<Long> getAllPendingBookingRequestsCount(){
        long response = customerService.getAllBookings();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/requests/approved/count")
    @ApiOperation(value = "Get Number of Booking Requests")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<Long> getAllApprovedPendingBookingRequestsCount(){
        long response = customerService.getAllApprovedBookings();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}

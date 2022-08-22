package com.group.crservice.service.impl;

import com.group.crservice.domain.entities.TransactionEntity;
import com.group.crservice.domain.entities.UserEntity;
import com.group.crservice.domain.entities.VehicleEntity;
import com.group.crservice.domain.enums.BookingStatus;
import com.group.crservice.exception.BadRequestException;
import com.group.crservice.repository.TransactionRepository;
import com.group.crservice.repository.UserRepository;
import com.group.crservice.repository.VehicleRepository;
import com.group.crservice.request.BookingRequest;
import com.group.crservice.response.BookingResponse;
import com.group.crservice.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.inject.Named;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final UserRepository userRepository;

    private final VehicleRepository vehicleRepository;

    private final TransactionRepository transactionRepository;

    @Override
    public BookingResponse bookVehicle(BookingRequest request) {
        VehicleEntity vehicle = vehicleRepository.findById(request.getVehicleId()).orElseThrow(() -> new BadRequestException("Vehicle Doesn't exist"));
        UserEntity user = userRepository.findById(request.getUserId()).orElseThrow(() -> new BadRequestException("User Doesn't exist"));

//        vehicle.setAvailable(false);
//        vehicleRepository.save(vehicle);

        TransactionEntity transactionEntity = TransactionEntity.builder()
                .vehicleId(vehicle)
                .userId(user)
                .pickUpDate(LocalDate.now().plusDays(1))
                .leaseDays(7)
                .transactionDescription("NA")
                .transactionType("Pay on Delivery")
                .amount(vehicle.getPricePerDay().multiply(BigDecimal.valueOf(7)))
                .status(BookingStatus.PENDING)
                .build();

        transactionRepository.save(transactionEntity);

        return fromEntityToResponse(transactionEntity);
    }

    @Override
    public String cancelBooking(long bookingId) {
        TransactionEntity transaction = transactionRepository.findById(bookingId).orElseThrow(() -> new BadRequestException("Vehicle Doesn't exist"));
        transaction.setStatus(BookingStatus.UNASSIGNED);
        long vid = transaction.getVehicleId().getId();
        VehicleEntity vehicle = vehicleRepository.findById(vid).orElseThrow(() -> new BadRequestException("Vehicle Doesn't exist"));

        vehicle.setAvailable(true);
        vehicleRepository.save(vehicle);
        transactionRepository.save(transaction);
        return "Vehicle Returned";
    }

    @Override
    public String approveBooking(long bookingId) {
        TransactionEntity transaction = transactionRepository.findById(bookingId).orElseThrow(() -> new BadRequestException("Vehicle Doesn't exist"));
        transaction.setStatus(BookingStatus.APPROVED);
        long vid = transaction.getVehicleId().getId();
        VehicleEntity vehicle = vehicleRepository.findById(vid).orElseThrow(() -> new BadRequestException("Vehicle Doesn't exist"));

        vehicle.setAvailable(false);
        vehicleRepository.save(vehicle);
        transactionRepository.save(transaction);
        return "Booking Approved";
    }

    @Override
    public List<BookingResponse> getAllBookingRequests() {
        return transactionRepository.findAll().stream().map(this::fromEntityToResponse).collect(Collectors.toList());
    }

    @Override
    public List<BookingResponse> getAllPending() {
        return transactionRepository.findAllByStatus(BookingStatus.PENDING).stream().map(this::fromEntityToResponse).collect(Collectors.toList());
    }

    @Override
    public long getAllBookings() {
        return transactionRepository.findAll().size();
    }

    @Override
    public long getAllApprovedBookings() {
        return transactionRepository.findAllByStatus(BookingStatus.APPROVED).size();
    }

    private BookingResponse fromEntityToResponse(TransactionEntity transaction){
        return BookingResponse.builder()
                .bookingId(transaction.getId())
                .vehicleName(transaction.getVehicleId().getModel())
                .fullName(transaction.getUserId().getFirstName() + " " + transaction.getUserId().getLastname())
                .vehicleId(transaction.getVehicleId().getId())
                .email(transaction.getUserId().getEmail())
                .userId(transaction.getUserId().getId())
                .vehicleCost(transaction.getVehicleId().getPricePerDay())
                .status(transaction.getStatus().name())
                .build();
    }
}

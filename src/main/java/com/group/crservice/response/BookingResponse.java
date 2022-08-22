package com.group.crservice.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BookingResponse {

    private long bookingId;

    private String vehicleName;

    private long vehicleId;

    private String fullName;

    private String email;

    private long userId;

    private BigDecimal vehicleCost;

    private String status;

}

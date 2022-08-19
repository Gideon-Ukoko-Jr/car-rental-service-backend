package com.group.crservice.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class VehicleResponse {

    private long vehicleId;

    private String plateNumber;

    private String model;

    private String color;

    private BigDecimal pricePerDay;

    private String imageUrl;

    private boolean isAvailable;
}

package com.group.crservice.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class AddVehicleRequest {

    @ApiModelProperty(notes = "Vehicle Plate Number", required = true)
    @NotEmpty
    @NotNull
    private String plateNumber;

    @ApiModelProperty(notes = "Vehicle Model", required = true)
    @NotEmpty
    @NotNull
    @Column(nullable = false)
    private String model;

    @ApiModelProperty(notes = "Vehicle Color", required = true)
    @NotEmpty
    @NotNull
    @Column(nullable = false)
    private String color;

    @ApiModelProperty(notes = "Vehicle Price Per Day", required = true)
    @NotEmpty
    @NotNull
    @Column(nullable = false)
    private BigDecimal pricePerDay;

    @ApiModelProperty(notes = "Vehicle Image Url", required = true)
    @NotEmpty
    @NotNull
    @Column(nullable = false)
    private String imageUrl;
}

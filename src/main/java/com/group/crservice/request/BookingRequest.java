package com.group.crservice.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class BookingRequest {

    @ApiModelProperty(notes = "User Id", required = true)
    @NotEmpty
    @NotNull
    private long userId;

    @ApiModelProperty(notes = "Vehicle Id", required = true)
    @NotEmpty
    @NotNull
    private long vehicleId;
}

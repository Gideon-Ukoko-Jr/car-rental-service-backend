package com.group.crservice.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class AddEmployeeRequest {

    @ApiModelProperty(notes = "User First Name", required = true)
    @NotEmpty
    @NotNull
    private String firstName;

    @ApiModelProperty(notes = "User Last Name", required = true)
    @NotEmpty
    @NotNull
    private String lastName;

    @ApiModelProperty(notes = "Employee Email", required = true)
    @NotEmpty
    @NotNull
    private String email;

    @ApiModelProperty(notes = "Employee Phone Number", required = true)
    @NotEmpty
    @NotNull
    private String phoneNumber;

    @ApiModelProperty(notes = "Birthday Format: yyyy-MM-dd", required = true)
    @NotEmpty
    @NotNull
    private String birthday;

    @ApiModelProperty(notes = "Employee Address", required = true)
    @NotEmpty
    @NotNull
    private String address;

    @ApiModelProperty(notes = "Employee Gender - Male | Female", required = true)
    @NotEmpty
    @NotNull
    private String gender;
}

package com.group.crservice.request;

import com.group.crservice.domain.enums.GenderEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
public class SignUpRequest {

    @ApiModelProperty(notes = "User First Name", required = true)
    @NotEmpty
    @NotNull
    private String firstName;

    @ApiModelProperty(notes = "User Last Name", required = true)
    @NotEmpty
    @NotNull
    private String lastName;

    @ApiModelProperty(notes = "User Email", required = true)
    @NotEmpty
    @NotNull
    private String email;

    @ApiModelProperty(notes = "User Password", required = true)
    @NotEmpty
    @NotNull
    private String password;

    @ApiModelProperty(notes = "User Phone Number", required = true)
    @NotEmpty
    @NotNull
    private String phoneNumber;

    @ApiModelProperty(notes = "Birthday Format: yyyy-MM-dd", required = true)
    @NotEmpty
    @NotNull
    private String birthday;

    @ApiModelProperty(notes = "User Address", required = true)
    @NotEmpty
    @NotNull
    private String address;

    @ApiModelProperty(notes = "User Gender", required = true)
    @NotEmpty
    @NotNull
    private String gender;
}

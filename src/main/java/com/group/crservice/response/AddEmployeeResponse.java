package com.group.crservice.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class AddEmployeeResponse {

    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private LocalDateTime date;
    private String status;

}

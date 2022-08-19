package com.group.crservice.controller;

import com.group.crservice.request.LoginRequest;
import com.group.crservice.request.SignUpRequest;
import com.group.crservice.response.LoginResponse;
import com.group.crservice.response.SignUpResponse;
import com.group.crservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Api(tags = "User Endpoints", description = "Handles Authentication and User Management")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/sign-up")
    @ApiOperation(value = "Handles User Registration")
    public ResponseEntity<SignUpResponse> register(@RequestBody SignUpRequest request){
        SignUpResponse response = userService.signUp(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    @ApiOperation(value = "Handles User Login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse response = userService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

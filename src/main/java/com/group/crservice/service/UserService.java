package com.group.crservice.service;

import com.group.crservice.request.LoginRequest;
import com.group.crservice.request.SignUpRequest;
import com.group.crservice.response.LoginResponse;
import com.group.crservice.response.SignUpResponse;
import com.group.crservice.response.UserResponse;

import java.util.List;

public interface UserService {

    SignUpResponse signUp(SignUpRequest request);

    LoginResponse login(LoginRequest request);

    List<UserResponse> getAllUsers();

    List<UserResponse> getAllEmployees();

}

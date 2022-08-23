package com.group.crservice.service;

import com.group.crservice.request.AddEmployeeRequest;
import com.group.crservice.response.AddEmployeeResponse;
import com.group.crservice.response.UserResponse;

import java.util.List;

public interface AdminService {

    AddEmployeeResponse createEmployee(AddEmployeeRequest request);

    String deactivateEmployee(long employeeId);

    List<UserResponse> getAllEmployees();
}

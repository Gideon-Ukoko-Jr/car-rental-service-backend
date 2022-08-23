package com.group.crservice.controller;

import com.group.crservice.request.AddEmployeeRequest;
import com.group.crservice.request.SignUpRequest;
import com.group.crservice.response.AddEmployeeResponse;
import com.group.crservice.response.SignUpResponse;
import com.group.crservice.response.UserResponse;
import com.group.crservice.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@Api(tags = "Admin Endpoints", description = "Handles Admin Functions")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/create-employee")
    @ApiOperation(value = "Handles Employee Creation")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AddEmployeeResponse> createEmployee(@RequestBody AddEmployeeRequest request){
        AddEmployeeResponse response = adminService.createEmployee(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/deactivate-employee/{id}")
    @ApiOperation(value = "Handles Employee Deactivation")
    public ResponseEntity<String> deactivateEmployee(@PathVariable Long id){
        String response = adminService.deactivateEmployee(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/employees")
    @ApiOperation(value = "Handles Getting All Employees")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllEmployees(){
        List<UserResponse> userResponseList = adminService.getAllEmployees();
        return new ResponseEntity<>(userResponseList, HttpStatus.OK);
    }
}

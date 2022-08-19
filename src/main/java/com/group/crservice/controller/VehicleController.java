package com.group.crservice.controller;

import com.group.crservice.request.AddVehicleRequest;
import com.group.crservice.response.VehicleResponse;
import com.group.crservice.service.VehicleService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/vehicles")
@Api(tags = "User Endpoints", description = "Handles Authentication and User Management")
@AllArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/visitors/all")
    public ResponseEntity<List<VehicleResponse>> getAllCars(){
        List<VehicleResponse> vehicleResponseList = vehicleService.getAvailableVehicles();
        return new ResponseEntity<>(vehicleResponseList, HttpStatus.OK);
    }

    @GetMapping("/visitors/{id}")
    public ResponseEntity<VehicleResponse> getCarById(@PathVariable Long id){
        VehicleResponse vehicle = vehicleService.getVehicleById(id);
        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }

    @PostMapping("/add-vehicle")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<VehicleResponse> addVehicle(@RequestBody AddVehicleRequest request) {
        VehicleResponse vehicleResponse = vehicleService.addVehicle(request);
        return new ResponseEntity<>(vehicleResponse, HttpStatus.OK);
    }
}

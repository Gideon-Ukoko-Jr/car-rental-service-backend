package com.group.crservice.controller;

import com.group.crservice.request.AddVehicleRequest;
import com.group.crservice.response.VehicleResponse;
import com.group.crservice.service.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "Vehicle Endpoints", description = "Handles Vehicle Endpoints and Management")
@AllArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/visitors/all")
    @ApiOperation(value = "Get All Available Vehicles")
    public ResponseEntity<List<VehicleResponse>> getAllCars(){
        List<VehicleResponse> vehicleResponseList = vehicleService.getAvailableVehicles();
        return new ResponseEntity<>(vehicleResponseList, HttpStatus.OK);
    }

    @GetMapping("/visitors/{id}")
    @ApiOperation(value = "Get Vehicle By Id")
    public ResponseEntity<VehicleResponse> getCarById(@PathVariable Long id){
        VehicleResponse vehicle = vehicleService.getVehicleById(id);
        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }

    @PostMapping("/add-vehicle")
    @ApiOperation(value = "Add Vehicle")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<VehicleResponse> addVehicle(@RequestBody AddVehicleRequest request) {
        VehicleResponse vehicleResponse = vehicleService.addVehicle(request);
        return new ResponseEntity<>(vehicleResponse, HttpStatus.OK);
    }

    @GetMapping("/available/count")
    @ApiOperation(value = "Get Number of Available Vehicles")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<Long> getAllAvailableVehiclesCount(){
        long response = vehicleService.getAvailableVehicleCount();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/booked-out/count")
    @ApiOperation(value = "Get Number of Booked Out Vehicles")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<Long> getAllBookedOutVehiclesCount(){
        long response = vehicleService.getVehiclesBookedOut();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

package com.group.crservice.service;

import com.group.crservice.domain.entities.VehicleEntity;
import com.group.crservice.request.AddVehicleRequest;
import com.group.crservice.response.VehicleResponse;

import java.util.List;

public interface VehicleService {

    VehicleResponse addVehicle(AddVehicleRequest request);

    List<VehicleResponse> getAvailableVehicles();

    VehicleResponse getVehicleById(long id);
    VehicleResponse fromVehicleEntityToVehicleResponse(VehicleEntity vehicleEntity);

    long getAvailableVehicleCount();

    long getVehiclesBookedOut();
}

package com.group.crservice.service.impl;

import com.group.crservice.domain.entities.VehicleEntity;
import com.group.crservice.exception.BadRequestException;
import com.group.crservice.exception.ResourceNotFoundException;
import com.group.crservice.repository.VehicleRepository;
import com.group.crservice.request.AddVehicleRequest;
import com.group.crservice.response.VehicleResponse;
import com.group.crservice.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    @Override
    public VehicleResponse addVehicle(AddVehicleRequest request) {

        String plateNumber = request.getPlateNumber();

        if (vehicleRepository.existsByPlateNumber(plateNumber)){
            throw new BadRequestException("Vehicle with Plate Number " + plateNumber + " already exists");
        }

        String model = request.getModel();
        String color = request.getColor();
        BigDecimal pricePerDay = request.getPricePerDay();
        String imageUrl = request.getImageUrl();

        VehicleEntity vehicleEntity = VehicleEntity.builder()
                .color(color)
                .imageUrl(imageUrl)
                .pricePerDay(pricePerDay)
                .plateNumber(plateNumber)
                .isAvailable(true)
                .model(model)
                .build();

        vehicleRepository.save(vehicleEntity);

        return fromVehicleEntityToVehicleResponse(vehicleEntity);
    }

    @Override
    public List<VehicleResponse> getAvailableVehicles() {
        return vehicleRepository.getAllAvailableVehicles().stream().map(this::fromVehicleEntityToVehicleResponse).collect(Collectors.toList());
    }

    @Override
    public VehicleResponse getVehicleById(long id) {
        VehicleEntity vehicleEntity = vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehicle with id " + id + " not found"));
        return fromVehicleEntityToVehicleResponse(vehicleEntity);
    }

    @Override
    public VehicleResponse fromVehicleEntityToVehicleResponse(VehicleEntity vehicleEntity) {
        return VehicleResponse.builder()
                .vehicleId(vehicleEntity.getId())
                .color(vehicleEntity.getColor())
                .imageUrl(vehicleEntity.getImageUrl())
                .plateNumber(vehicleEntity.getPlateNumber())
                .pricePerDay(vehicleEntity.getPricePerDay())
                .model(vehicleEntity.getModel())
                .isAvailable(vehicleEntity.isAvailable())
                .build();
    }
}

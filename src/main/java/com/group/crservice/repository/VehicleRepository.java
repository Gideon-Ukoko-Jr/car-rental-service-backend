package com.group.crservice.repository;

import com.group.crservice.domain.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

    Optional<VehicleEntity> findByPlateNumber(String plateNumber);

    boolean existsByPlateNumber(String plateNumber);

    @Query("select v from VehicleEntity v where v.isAvailable = true")
    List<VehicleEntity> getAllAvailableVehicles();

    @Query("select v from VehicleEntity v order by v.model asc")
    List<VehicleEntity> getAllVehicles();
}

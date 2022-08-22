package com.group.crservice.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vehicle")
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, unique = true)
    private String plateNumber;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private BigDecimal pricePerDay;

    @Column(nullable = false)
    private String imageUrl;

    private boolean isAvailable;

    public VehicleEntity(String plateNumber, String model, String color, BigDecimal pricePerDay, String imageUrl, boolean isAvailable) {
        this.plateNumber = plateNumber;
        this.model = model;
        this.color = color;
        this.pricePerDay = pricePerDay;
        this.imageUrl = imageUrl;
        this.isAvailable = isAvailable;
    }
}

package com.group.crservice.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 20)
    private String regNum;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String model;

    private boolean isAvailable;

    public VehicleEntity(long id, String regNum, String color, String model, boolean isAvailable) {
        this.id = id;
        this.regNum = regNum;
        this.color = color;
        this.model = model;
        this.isAvailable = isAvailable;
    }

    public VehicleEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

package com.group.crservice.entity.entities;

import com.group.crservice.entity.enums.ERole;
import com.group.crservice.entity.enums.GenderEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "cars")
public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private String regNum;
    private String color;
    private String model;
    private boolean isAvailable;

    public VehicleEntity() {
    }

    public VehicleEntity(long id, String regNum, String color, String model, boolean isAvailable) {
        this.id = id;
        this.regNum = regNum;
        this.color = color;
        this.model = model;
        this.isAvailable = isAvailable;
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

    public void setRegNum(String regNum) {this.regNum = regNum;}

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel(String model){ return model;}

    public void setModel(String model) {
        this.model = model;
    }

    public boolean getAvailable(boolean isAvailable) { return isAvailable;}

    public void setAvailable(boolean isAvailable){this.isAvailable = isAvailable;}
}

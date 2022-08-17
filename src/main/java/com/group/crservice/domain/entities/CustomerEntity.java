package com.group.crservice.domain.entities;

import com.group.crservice.domain.enums.ERole;

import javax.persistence.*;
@Entity
@Table(name = "customer")

public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private String nextOfKin;
    private String occupation;
    private String licenseId;

    public CustomerEntity() {
    }

    public CustomerEntity(long id, String nextOfKin, String occupation, String licenseId) {
        this.id = id;
        this.nextOfKin = nextOfKin;
        this.occupation = occupation;
        this.licenseId = licenseId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNextOfKin() {
        return nextOfKin;
    }
    public void setNextOfKin(String nextOfKin) {this.nextOfKin = nextOfKin;}

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getLicenseId(String licenseId){ return licenseId;}

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }
}

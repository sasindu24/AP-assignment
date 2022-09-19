package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="booking")
public class Orders {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    private String vehicleType;
    private String vehicleBranch;
    private String driverName;
    private String status;
    private String customer;
    private String startingFrom;
    private  String destination;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleBranch() {
        return vehicleBranch;
    }

    public void setVehicleBranch(String vehicleBranch) {
        this.vehicleBranch = vehicleBranch;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getStartingFrom() {
        return startingFrom;
    }

    public void setStartingFrom(String startingFrom) {
        this.startingFrom = startingFrom;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}

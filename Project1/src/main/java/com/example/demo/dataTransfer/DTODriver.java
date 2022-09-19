package com.example.demo.dataTransfer;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class DTODriver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    private String driver_id;

    private String branch;

    private String vehicle_id;

    private String vehicle_type;

    private String address;

    private String mobileNumber;

    private String imageName;
}

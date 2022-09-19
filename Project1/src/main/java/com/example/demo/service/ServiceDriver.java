package com.example.demo.service;

import com.example.demo.model.Driver;
import com.example.demo.repo.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceDriver {
    @Autowired
    DriverRepository driverRepository;
        public List<Driver> showAllDrivers() {
            return driverRepository.findAll();
        }
        public void addDriver(Driver driver) {
            driverRepository.save(driver);
        }

        public void deleteDriver(int id) {
            driverRepository.deleteById(id);
        }

        public Optional<Driver> getDriverById(int id) {
            return driverRepository.findById(id);
        }

        public List<Driver> lisAll(String keyword) {
            if (keyword != null) {
                return driverRepository.search(keyword);
            }else {
                return showAllDrivers();
            }
    }
}

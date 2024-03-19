package com.example.demo.Driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Optional<Driver> getDriverById(Long id) {
        return driverRepository.findById(id);
    }

    public void addDriver(Driver driver) {
        driverRepository.save(driver);
    }

    public void updateDriver(Long id, Driver updatedDriver) {
        if (driverRepository.existsById(id)) {
            updatedDriver.setId(id);
            driverRepository.save(updatedDriver);
        } else {
            // Handle error
        }
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}
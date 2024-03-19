package com.example.demo.Driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/driver")
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @GetMapping("/{driverId}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long driverId) {
        Optional<Driver> driver = driverService.getDriverById(driverId);
        return driver.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public void addDriver(@RequestBody Driver driver) {
        driverService.addDriver(driver);
    }

    @PutMapping("/{driverId}")
    public ResponseEntity<Object> updateDriver(@PathVariable Long driverId, @RequestBody Driver updatedDriver) {
        try {
            driverService.updateDriver(driverId, updatedDriver);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{driverId}")
    public ResponseEntity<Object> deleteDriver(@PathVariable Long driverId) {
        try {
            driverService.deleteDriver(driverId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
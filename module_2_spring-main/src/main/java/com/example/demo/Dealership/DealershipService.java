package com.example.demo.Dealership;

import com.example.demo.Driver.Driver;
import com.example.demo.Driver.DriverRepository;
import com.example.demo.cars.Car;
import com.example.demo.cars.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealershipService {

    private final DriverRepository driverRepository;
    private final DealershipRepository dealershipRepository;
    private final CarRepository carRepository;

    @Autowired
    public DealershipService(DriverRepository driverRepository, DealershipRepository dealershipRepository, CarRepository carRepository) {
        this.driverRepository = driverRepository;
        this.dealershipRepository = dealershipRepository;
        this.carRepository = carRepository;
    }

    public List<Dealership> getAllDealerships() {
        return dealershipRepository.findAll();
    }

    public Optional<Dealership> getDealershipById(Long id) {
        return dealershipRepository.findById(id);
    }

    public void addDealership(Dealership dealership) {
        dealershipRepository.save(dealership);
    }

    public void updateDealership(Long id, Dealership updatedDealership) {
        if (dealershipRepository.existsById(id)) {
            updatedDealership.setId(id);
            dealershipRepository.save(updatedDealership);
        } else {

        }
    }

    public void deleteDealership(Long id) {
        dealershipRepository.deleteById(id);
    }

    public void addToCar(Long dealership_id, Long car_id) {
        Dealership dealership=dealershipRepository.findById(dealership_id).orElseThrow();
        Car car=carRepository.findById(car_id).orElseThrow();
        dealership.setCars(car);
        dealershipRepository.save(dealership);
    }

    public void addDriver(long id, long driverId){
        Driver driver=driverRepository.findById(driverId).orElseThrow(() -> new IllegalStateException("Driver not found"));
        Dealership dealership=dealershipRepository.findById(id).orElseThrow(() -> new IllegalStateException("Driver not found"));
        dealership.addDriver(driver);
        dealershipRepository.save(dealership);
    }
}


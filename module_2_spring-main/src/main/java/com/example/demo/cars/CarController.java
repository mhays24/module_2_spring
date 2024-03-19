package com.example.demo.cars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/car")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getCars();
    }

    @GetMapping("/{carId}")
    public ResponseEntity<Car> getCarById(@PathVariable Long carId) {
        Optional<Car> car = carService.findCar(carId);
        return car.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public void addCar(@RequestBody Car car) {
        carService.addNewCar(car);
    }

    @PutMapping("/{carId}")
    public ResponseEntity<Object> updateCar(@PathVariable Long carId, @RequestBody Car updatedCar) {
        try {
            carService.updateCar(carId, updatedCar);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<Object> deleteCar(@PathVariable Long carId) {
        try {
            carService.deleteCar(carId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
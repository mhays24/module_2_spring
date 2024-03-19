package com.example.demo.cars;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public void addNewCar(Car car) {
        Optional<Car> carOptional = carRepository
                .findsCarByColor(car.getColor());
        if (carOptional.isPresent()) {
            throw new IllegalStateException("color taken");
        }
        carRepository.save(car);
    }

    public void deleteCar(Long carId) {
        boolean exists = carRepository.existsById(carId);
        if (!exists) {
            throw new IllegalStateException("car with id " + carId + " does not exists");
        }
        carRepository.deleteById(carId);
    }

    @Transactional
    public void updateCar(Long carId,
                                   Car updatedCar) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + carId + " does not exist"));
        updatedCar.setId(carId);
    }

    public Optional<Car> findCar(Long carId) {
        return carRepository.findById(carId);
    }
};

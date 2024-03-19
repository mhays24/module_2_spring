package com.example.demo.Driver;

import com.example.demo.cars.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("drivers")
public class DriverConfig {

    @Bean
    CommandLineRunner commandLineRunner1(DriverRepository driverRepository, CarRepository carRepository) {
        return args -> {
            Driver driver1 = new Driver("John Doe", 30);
            Driver driver2 = new Driver("Jane Smith", 25);
            driverRepository.saveAll(List.of(driver1, driver2));
        };
    }
}
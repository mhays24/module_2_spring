package com.example.demo.Dealership;

import com.example.demo.cars.Car;
import com.example.demo.cars.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("dealerships")
public class DealershipConfig {

    @Bean
    CommandLineRunner commandLineRunner2(DealershipRepository dealershipRepository, CarRepository carRepository) {
        return args -> {
            Dealership dealership1 = new Dealership("ABC Motors");
            Dealership dealership2 = new Dealership("XYZ Autos");
            dealershipRepository.saveAll(List.of(dealership1, dealership2));
        };
    }
}
package com.example.demo.cars;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("cars")
public class CarConfig {
    @Bean
    CommandLineRunner commandLineRunner(CarRepository carRepository){
        return args -> {
            Car car1 = new Car("Toyota", "Camry", "2023", "Black");

            Car car2 = new Car("Honda", "Civic", "2022", "Silver");

            Car car3 = new Car("Ford", "Mustang", "2024", "Red");
            carRepository.saveAll(List.of(car1,car2,car3));
        };
    }
}

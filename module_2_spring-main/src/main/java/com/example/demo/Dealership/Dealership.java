package com.example.demo.Dealership;

import com.example.demo.Driver.Driver;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import com.example.demo.cars.Car;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Dealership {
    @Id
    @SequenceGenerator(
            name = "dealership_sequence",
            sequenceName = "dealership_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "dealership_sequence"
    )
    private Long id;
    private String name;



//    @JsonIgnore
//    @OneToMany(mappedBy = "dealership")
//    private List<Car> cars;

    @ManyToMany
    @JoinTable(
            name = "driver_car",
            joinColumns = @JoinColumn(name = "dealership_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id"))
    Set<Driver> driver = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car cars;

    public Dealership() {
    }

    public Dealership(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Dealership(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<Driver> getDriver() {
        return driver;
    }

    public void setDriver(Set<Driver> driver) {
        this.driver = driver;
    }

    public void addDriver(Driver driver) {
        this.driver.add(driver);
    }

    public Car getCars() {
        return cars;
    }

    public void setCars(Car cars) {
        this.cars = cars;
    }
}
package com.example.demo.Driver;

import com.example.demo.Dealership.Dealership;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import com.example.demo.cars.Car;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Driver {
    @Id
    @SequenceGenerator(
            name = "driver_sequence",
            sequenceName = "driver_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "driver_sequence"
    )
    private Long id;
    private String name;
    private int age;


//    @ManyToMany
//    @JoinTable(
//            name = "driver_car",
//            joinColumns = @JoinColumn(name = "driver_id"),
//            inverseJoinColumns = @JoinColumn(name = "car_id"))
//    Set<Car> cars = new HashSet<>();
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "dealership_id", referencedColumnName = "id")
//    private Dealership dealership;

    @JsonIgnore
    @ManyToMany(mappedBy = "driver")
    private Set<Dealership> dealership = new HashSet<>();

    public Driver(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Driver(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Driver() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
//
    public Set<Dealership> getDealership() {
        return dealership;
    }

    public void setDealership(Set<Dealership> dealership) {
        this.dealership = dealership;
    }
}
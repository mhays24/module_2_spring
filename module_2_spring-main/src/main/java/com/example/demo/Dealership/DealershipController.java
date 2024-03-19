package com.example.demo.Dealership;

import com.sun.jdi.LongValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/dealership")
public class DealershipController {

    private final DealershipService dealershipService;

    @Autowired
    public DealershipController(DealershipService dealershipService) {
        this.dealershipService = dealershipService;
    }

    @GetMapping
    public List<Dealership> getAllDealerships() {
        return dealershipService.getAllDealerships();
    }

    @GetMapping("/{dealershipId}")
    public ResponseEntity<Dealership> getDealershipById(@PathVariable Long dealershipId) {
        Optional<Dealership> dealership = dealershipService.getDealershipById(dealershipId);
        return dealership.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public void addDealership(@RequestBody Dealership dealership) {
        dealershipService.addDealership(dealership);
    }

    @PutMapping("/{dealershipId}")
    public ResponseEntity<Object> updateDealership(@PathVariable Long dealershipId, @RequestBody Dealership updatedDealership) {
        try {
            dealershipService.updateDealership(dealershipId, updatedDealership);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/addcar/{dealershipId}/{carId}")
    public void addCarToDealership(@PathVariable Long dealershipId, @PathVariable Long carId){
        dealershipService.addToCar(dealershipId, carId);
    }

    @DeleteMapping("/{dealershipId}")
    public ResponseEntity<Object> deleteDealership(@PathVariable Long dealershipId) {
        try {
            dealershipService.deleteDealership(dealershipId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/adddriver/{dealershipId}/{driverId}")
    public void addDriverToDealership(@PathVariable Long dealershipId, @PathVariable Long driverId){
        dealershipService.addDriver(dealershipId, driverId);
    }
}
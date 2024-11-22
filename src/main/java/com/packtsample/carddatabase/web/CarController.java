package com.packtsample.carddatabase.web;

import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.packtsample.carddatabase.domain.Car;
import com.packtsample.carddatabase.domain.CarRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
public class CarController {

    @Autowired
    private final CarRepository repository;

    public CarController(CarRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cars")
    public Iterable<Car> getCars() {
        return repository.findAll();
    }
    
    @PostMapping("/cars")
    public Car addCar(@RequestBody Car car) {
        //TODO: process POST request
        
        return repository.save(car);
    }

    @PutMapping("cars/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car carDetails) {
        Optional<Car>  getCar = repository.findById(id);
        if(getCar.isPresent()){
            Car car = new Car();
            car.setBrand(carDetails.getBrand());
            car.setColor(carDetails.getColor());
            car.setModel(carDetails.getModel());
            car.setModelYear(carDetails.getModelYear());
            car.setRegistrationNumber(carDetails.getRegistrationNumber());
            car.setPrice(carDetails.getPrice());

            return repository.save(car);
        }else{
            throw new RuntimeException("Car not found with id: " + id);
        }
    }
    

}

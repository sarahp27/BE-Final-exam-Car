package com.road_runner.car;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
@CrossOrigin("*")
public class CarController {
    @Autowired
    public CarRepo cRepo;

    @GetMapping(value = "/getAll")
    public List<Car> getallCars(){
        return cRepo.findAll();
    }

    @GetMapping(value = "/get/{id}")
    public Car getCarbyId(@PathVariable Long id){
        return cRepo.findById(id).orElse(null);
    }


    @PostMapping(value = "/post")
    public void postCar(@RequestBody Car car){
        cRepo.save(car);
    }
    
}

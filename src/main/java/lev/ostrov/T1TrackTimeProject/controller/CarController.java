package lev.ostrov.T1TrackTimeProject.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lev.ostrov.T1TrackTimeProject.model.Car;
import lev.ostrov.T1TrackTimeProject.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@Validated
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    @Operation(summary = "Get all cars list")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping
    @Operation(summary = "Create or update car note")
    public void addCar(@Valid @RequestBody Car car) {
        carService.addCar(car);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete car by ID")
    public void deleteCarById(@PathVariable Long id) {
        carService.deleteCarById(id);
    }

    @DeleteMapping("/model/{model}")
    @Operation(summary = "Delete cars by model")
    public void deleteCarsByModel(@PathVariable String model) {
        carService.deleteCarsByModel(model);
    }

    @DeleteMapping
    @Operation(summary = "Delete all cars")
    public void deleteAllCars() {
        carService.deleteAllCars();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleException(MethodArgumentNotValidException exception) {
    }
}
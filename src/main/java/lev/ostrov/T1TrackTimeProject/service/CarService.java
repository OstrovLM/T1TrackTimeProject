package lev.ostrov.T1TrackTimeProject.service;

import lev.ostrov.T1TrackTimeProject.annotation.TrackAsyncTime;
import lev.ostrov.T1TrackTimeProject.annotation.TrackTime;
import lev.ostrov.T1TrackTimeProject.model.Car;
import lev.ostrov.T1TrackTimeProject.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CarService {
    private final CarRepository carRepository;

    @TrackTime
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @TrackAsyncTime
    public void addCar(Car car) {
        carRepository.save(car);
    }

    @TrackTime
    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    @TrackTime
    public void deleteCarsByModel(String model) {
        carRepository.deleteByModel(model);
    }

    @TrackTime
    public void deleteAllCars() {
        carRepository.deleteAll();
    }
}

package lev.ostrov.T1TrackTimeProject.repository;

import lev.ostrov.T1TrackTimeProject.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long>{
    void deleteByModel(String model);
}

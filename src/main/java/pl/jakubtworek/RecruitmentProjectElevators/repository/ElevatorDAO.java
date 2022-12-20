package pl.jakubtworek.RecruitmentProjectElevators.repository;

import org.springframework.http.ResponseEntity;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

import java.util.List;

public interface ElevatorDAO {
    void pickup();
    Elevator update();
    void step();
    List<Elevator> status();
}
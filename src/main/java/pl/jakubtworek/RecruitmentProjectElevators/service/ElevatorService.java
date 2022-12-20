package pl.jakubtworek.RecruitmentProjectElevators.service;

import org.springframework.http.ResponseEntity;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

import java.util.List;

public interface ElevatorService {
    void pickup();
    Elevator update();
    void step();
    List<Elevator> status();
}
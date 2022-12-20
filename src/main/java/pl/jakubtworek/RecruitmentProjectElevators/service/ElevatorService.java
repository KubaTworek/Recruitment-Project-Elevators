package pl.jakubtworek.RecruitmentProjectElevators.service;

import org.springframework.http.ResponseEntity;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

import java.util.List;

public interface ElevatorService {
    void pickup(int sourceFloor, int destinationFloor);
    void step();
    List<Elevator> status();
}
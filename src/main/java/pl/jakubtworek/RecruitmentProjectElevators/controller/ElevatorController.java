package pl.jakubtworek.RecruitmentProjectElevators.controller;

import org.springframework.http.ResponseEntity;
import pl.jakubtworek.RecruitmentProjectElevators.model.ElevatorResponse;

import java.util.List;

public interface ElevatorController {
    void pickup();

    ResponseEntity<ElevatorResponse> update();

    void step();

    ResponseEntity<List<ElevatorResponse>> status();
}
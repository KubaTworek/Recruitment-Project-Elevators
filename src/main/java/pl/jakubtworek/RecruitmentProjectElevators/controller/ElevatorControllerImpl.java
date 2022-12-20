package pl.jakubtworek.RecruitmentProjectElevators.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import pl.jakubtworek.RecruitmentProjectElevators.model.ElevatorResponse;
import pl.jakubtworek.RecruitmentProjectElevators.service.ElevatorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ElevatorControllerImpl implements ElevatorController{
    private final ElevatorService elevatorService;

    @Override
    public void pickup() {

    }

    @Override
    public ResponseEntity<ElevatorResponse> update() {
        return null;
    }

    @Override
    public void step() {

    }

    @Override
    public ResponseEntity<List<ElevatorResponse>> status() {
        return null;
    }
}

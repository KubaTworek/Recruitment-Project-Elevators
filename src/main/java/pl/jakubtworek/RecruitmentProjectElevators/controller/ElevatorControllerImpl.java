package pl.jakubtworek.RecruitmentProjectElevators.controller;

import org.springframework.http.ResponseEntity;
import pl.jakubtworek.RecruitmentProjectElevators.model.ElevatorResponse;

import java.util.List;

public class ElevatorControllerImpl implements ElevatorController{
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

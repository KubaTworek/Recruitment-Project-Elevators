package pl.jakubtworek.RecruitmentProjectElevators.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pl.jakubtworek.RecruitmentProjectElevators.model.*;
import pl.jakubtworek.RecruitmentProjectElevators.service.ElevatorService;

import java.util.List;

@RestController
@RequestMapping("/elevators")
public class ElevatorController {
    private final ElevatorService elevatorService;

    public ElevatorController(ElevatorService elevatorService) {
        this.elevatorService = elevatorService;
    }

    @CrossOrigin
    @PostMapping("/pickup")
    public void pickup(@RequestParam int sourceFloor,
                       @RequestParam int destinationFloor) {
        elevatorService.pickup(sourceFloor, destinationFloor);
    }

    @CrossOrigin
    @PutMapping("/step")
    public void step() {
        elevatorService.step();
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<ElevatorResponse>> status() {
        List<ElevatorResponse> response = elevatorService.status()
                .stream()
                .map(Elevator::convertToResponse)
                .toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
package pl.jakubtworek.RecruitmentProjectElevators.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;
import pl.jakubtworek.RecruitmentProjectElevators.model.ElevatorResponse;
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
    @PostMapping("/update/{id}")
    public void update(@PathVariable int id,
                       @RequestParam int floor) {
        elevatorService.update(id, floor);
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
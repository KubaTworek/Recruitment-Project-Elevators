package pl.jakubtworek.RecruitmentProjectElevators.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakubtworek.RecruitmentProjectElevators.exception.ElevatorNotFoundException;
import pl.jakubtworek.RecruitmentProjectElevators.exception.FloorNotFoundException;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;
import pl.jakubtworek.RecruitmentProjectElevators.model.ElevatorResponse;
import pl.jakubtworek.RecruitmentProjectElevators.service.ElevatorService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/elevators")
public class ElevatorController {
    private final ElevatorService elevatorService;

    public ElevatorController(ElevatorService elevatorService) {
        this.elevatorService = elevatorService;
    }

    @CrossOrigin
    @PostMapping("/update/{id}")
    public ResponseEntity<ElevatorResponse> update(@PathVariable int id,
                                                   @RequestParam int floor)
            throws ElevatorNotFoundException, FloorNotFoundException {

        if (floor < 0 || floor > 9) throw new FloorNotFoundException("There are only 10 floors");

        ElevatorResponse response = elevatorService.update(id, floor).convertToResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/pickup")
    public ResponseEntity<ElevatorResponse> pickup(@RequestParam int sourceFloor,
                                                   @RequestParam int destinationFloor)
            throws ElevatorNotFoundException, FloorNotFoundException {

        if (sourceFloor < 0 || sourceFloor > 9) throw new FloorNotFoundException("There are only 10 floors");
        if (destinationFloor < 0 || destinationFloor > 9) throw new FloorNotFoundException("There are only 10 floors");

        ElevatorResponse response = elevatorService.pickup(sourceFloor, destinationFloor).convertToResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("/step")
    public ResponseEntity<List<ElevatorResponse>> step() throws ElevatorNotFoundException {
        List<ElevatorResponse> response = elevatorService.step()
                .stream()
                .map(Elevator::convertToResponse)
                .collect(Collectors.toList());

        if (response.isEmpty()) throw new ElevatorNotFoundException("There are not elevators to move");

        return new ResponseEntity<>(response, HttpStatus.OK);
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
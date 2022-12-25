package pl.jakubtworek.RecruitmentProjectElevators.service;

import pl.jakubtworek.RecruitmentProjectElevators.exception.ElevatorNotFoundException;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

import java.util.List;

public interface ElevatorService {
    Elevator update(int id, int floor) throws ElevatorNotFoundException;

    Elevator pickup(int sourceFloor, int destinationFloor) throws ElevatorNotFoundException;

    List<Elevator> step() throws ElevatorNotFoundException;

    List<Elevator> status();
}
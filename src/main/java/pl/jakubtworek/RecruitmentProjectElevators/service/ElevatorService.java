package pl.jakubtworek.RecruitmentProjectElevators.service;

import pl.jakubtworek.RecruitmentProjectElevators.exception.ElevatorNotFoundException;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

import java.util.List;

public interface ElevatorService {
    void update(int id, int floor) throws ElevatorNotFoundException;

    void pickup(int sourceFloor, int destinationFloor) throws ElevatorNotFoundException;

    void step() throws ElevatorNotFoundException;

    List<Elevator> status();
}
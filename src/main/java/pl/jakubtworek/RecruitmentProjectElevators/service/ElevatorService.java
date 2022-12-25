package pl.jakubtworek.RecruitmentProjectElevators.service;

import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

import java.util.List;

public interface ElevatorService {
    void update(int id, int floor);

    void pickup(int sourceFloor, int destinationFloor);

    void step();

    List<Elevator> status();
}
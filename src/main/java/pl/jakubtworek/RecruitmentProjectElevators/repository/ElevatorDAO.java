package pl.jakubtworek.RecruitmentProjectElevators.repository;

import pl.jakubtworek.RecruitmentProjectElevators.exception.ElevatorNotFoundException;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

import java.util.List;
import java.util.Optional;

public interface ElevatorDAO {
    Elevator update(int id, Integer floorDestination, boolean isUserFloor) throws ElevatorNotFoundException;

    Elevator update(int id, int floorDestination) throws ElevatorNotFoundException;

    Elevator update(int id) throws ElevatorNotFoundException;

    List<Elevator> findAll();

    Optional<Elevator> findById(int id);

    List<Elevator> findElevatorToMove();
}
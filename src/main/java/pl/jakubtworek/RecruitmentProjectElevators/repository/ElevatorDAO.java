package pl.jakubtworek.RecruitmentProjectElevators.repository;

import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

import java.util.List;
import java.util.Optional;

public interface ElevatorDAO {
    Elevator update(int id, int actualFloor, Integer floorDestination, boolean isUserFloor);

    List<Elevator> findAll();

    Optional<Elevator> findById(int id);

    List<Elevator> findElevatorToMove();
}
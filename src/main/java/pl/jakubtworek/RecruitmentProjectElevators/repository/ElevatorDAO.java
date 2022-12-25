package pl.jakubtworek.RecruitmentProjectElevators.repository;

import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

import java.util.List;
import java.util.Optional;

public interface ElevatorDAO {
    Elevator updateForPickup(int id, Integer floorDestination, boolean isUserFloor);

    Elevator updateForStep(int id);

    List<Elevator> findAll();

    Optional<Elevator> findById(int id);

    List<Elevator> findElevatorToMove();
}
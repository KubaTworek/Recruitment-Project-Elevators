package pl.jakubtworek.RecruitmentProjectElevators.repository;

import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

import java.util.*;

public interface ElevatorDAO {
    Elevator update(int id, int actualFloor, int userFloor, Integer floorDestination, boolean isMovingDown, boolean isMovingUp);

    List<Elevator> findAll();

    Optional<Elevator> findById(int id);

    Optional<Elevator> findElevatorNotMoving();

    List<Elevator> findElevatorToMove();
}
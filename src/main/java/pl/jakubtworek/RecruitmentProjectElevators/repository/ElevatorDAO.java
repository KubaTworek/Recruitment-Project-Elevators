package pl.jakubtworek.RecruitmentProjectElevators.repository;

import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

import java.util.*;

public interface ElevatorDAO {
    Elevator update(int id, int actualFloor, Integer floorDestination, boolean isMovingDown, boolean isMovingUp, boolean isDestination);

    List<Elevator> findAll();

    Optional<Elevator> findById(int id);

    Optional<Elevator> findElevatorNotMoving();

    Optional<Elevator> findElevatorToMoveOnFloor(int destination);

    List<Elevator> findElevatorToMove();
}
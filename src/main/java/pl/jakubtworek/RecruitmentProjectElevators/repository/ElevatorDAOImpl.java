package pl.jakubtworek.RecruitmentProjectElevators.repository;

import org.springframework.stereotype.Repository;
import pl.jakubtworek.RecruitmentProjectElevators.data.Elevators;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;
import pl.jakubtworek.RecruitmentProjectElevators.model.Floor;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ElevatorDAOImpl implements ElevatorDAO {
    private final Elevators elevators;

    public ElevatorDAOImpl(Elevators elevators) {
        this.elevators = elevators;
    }

    @Override
    public Elevator update(int id, int actualFloor, Integer floorDestination, boolean isMovingDown, boolean isMovingUp, boolean isDestination) {
        Elevator elevator = elevators.getElevators().stream()
                .filter(e -> e.getId() == id)
                .map(e -> {
                    Floor.TypeOfTarget type = (isDestination) ? Floor.TypeOfTarget.DESTINATION : Floor.TypeOfTarget.USER;
                    e.setNumberOfFloor(actualFloor);
                    e.setMovingUp(isMovingUp);
                    e.setMovingDown(isMovingDown);
                    if (floorDestination != null && e.getPlannedFloors().stream().filter(e2 -> e2.getNumberOfFloor() == floorDestination).toList().size() == 0) e.getPlannedFloors().add(new Floor(floorDestination, type));
                    return e;
                })
                .findFirst()
                .orElse(null);
        return elevator;
    }

    @Override
    public List<Elevator> findAll() {
        return elevators.getElevators();
    }

    @Override
    public Optional<Elevator> findById(int id) {
        return elevators.getElevators()
                .stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }

    @Override
    public Optional<Elevator> findElevatorNotMoving() {
        return elevators.getElevators().stream()
                .filter(e -> !e.isMovingDown())
                .filter(e -> !e.isMovingUp())
                .findFirst();
    }

    @Override
    public Optional<Elevator> findElevatorToMoveOnFloor(int destination) {
        return elevators.getElevators().stream()
                .filter(e -> e.getPlannedFloors().peek() != null && e.getPlannedFloors().peek().getNumberOfFloor() < destination)
                .findFirst();
    }

    @Override
    public List<Elevator> findElevatorToMove() {
        return elevators.getElevators().stream()
                .filter(e -> e.getPlannedFloors().size() != 0)
                .collect(Collectors.toList());
    }
}
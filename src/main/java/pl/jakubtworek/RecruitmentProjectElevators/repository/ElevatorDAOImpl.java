package pl.jakubtworek.RecruitmentProjectElevators.repository;

import org.springframework.stereotype.Repository;
import pl.jakubtworek.RecruitmentProjectElevators.data.Elevators;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ElevatorDAOImpl implements ElevatorDAO{
    private final Elevators elevators = Elevators.getInstance();

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
    public List<Elevator> findElevatorToMove() {
        return elevators.getElevators().stream()
                .filter(e -> e.getPlannedFloors().size() != 0)
                .collect(Collectors.toList());
    }
}

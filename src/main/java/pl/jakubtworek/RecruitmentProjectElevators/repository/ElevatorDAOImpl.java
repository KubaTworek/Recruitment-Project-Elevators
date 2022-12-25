package pl.jakubtworek.RecruitmentProjectElevators.repository;

import org.springframework.stereotype.Repository;
import pl.jakubtworek.RecruitmentProjectElevators.data.Elevators;
import pl.jakubtworek.RecruitmentProjectElevators.exception.ElevatorNotFoundException;
import pl.jakubtworek.RecruitmentProjectElevators.factories.ElevatorFactory;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;
import pl.jakubtworek.RecruitmentProjectElevators.model.Floor;
import pl.jakubtworek.RecruitmentProjectElevators.model.TypeOfTarget;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ElevatorDAOImpl implements ElevatorDAO {
    private final ElevatorFactory elevatorFactory;
    private final Elevators elevators;

    public ElevatorDAOImpl(ElevatorFactory elevatorFactory, Elevators elevators) {
        this.elevatorFactory = elevatorFactory;
        this.elevators = elevators;
    }

    @Override
    public Elevator update(int id, Integer floorDestination, boolean isUserFloor) throws ElevatorNotFoundException {
        Elevator elevator = findById(id)
                .orElseThrow(() -> new ElevatorNotFoundException("There are no elevator with that id: " + id));

        return elevatorFactory.create(floorDestination, isUserFloor).updateElevator(elevator);
    }

    @Override
    public Elevator update(int id, int floor) throws ElevatorNotFoundException {
        Elevator elevator = findById(id)
                .orElseThrow(() -> new ElevatorNotFoundException("There are no elevator with that id: " + id));

        return elevatorFactory.create(floor).updateElevator(elevator);
    }

    @Override
    public Elevator update(int id) throws ElevatorNotFoundException {
        Elevator elevator = findById(id)
                .orElseThrow(() -> new ElevatorNotFoundException("There are no elevator with that id: " + id));

        return elevatorFactory.create().updateElevator(elevator);
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
    public List<Elevator> findElevatorToMove() {
        return elevators.getElevators().stream()
                .filter(e -> e.getPlannedFloors().size() != 0)
                .collect(Collectors.toList());
    }
}
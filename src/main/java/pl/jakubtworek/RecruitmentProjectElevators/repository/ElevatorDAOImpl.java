package pl.jakubtworek.RecruitmentProjectElevators.repository;

import org.springframework.stereotype.Repository;
import pl.jakubtworek.RecruitmentProjectElevators.data.Elevators;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;
import pl.jakubtworek.RecruitmentProjectElevators.model.Floor;
import pl.jakubtworek.RecruitmentProjectElevators.model.TypeOfTarget;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ElevatorDAOImpl implements ElevatorDAO {
    private final Elevators elevators;

    public ElevatorDAOImpl(Elevators elevators) {
        this.elevators = elevators;
    }

    @Override
    public Elevator update(int id, Integer floorDestination, boolean isUserFloor) {
        Elevator elevator = findById(id).orElse(null);

        addNextFloor(isUserFloor, floorDestination, elevator);

        return elevator;
    }

    @Override
    public Elevator update(int id) {
        Elevator elevator = findById(id).orElse(null);

        boolean isUserFloor = false;
        Integer floorDestination = null;
        int newFloor = elevator.getPlannedFloors().poll().numberOfFloor();
        if (elevator.getPlannedFloors().size() > 0) {
            Floor floor = elevator.getPlannedFloors().poll();
            isUserFloor = floor.typeOfTarget() == TypeOfTarget.USER;
            floorDestination = floor.numberOfFloor();
        }

        elevator.setNumberOfFloor(newFloor);
        addNextFloor(isUserFloor, floorDestination, elevator);

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
    public List<Elevator> findElevatorToMove() {
        return elevators.getElevators().stream()
                .filter(e -> e.getPlannedFloors().size() != 0)
                .collect(Collectors.toList());
    }

    private void addNextFloor(boolean isUserFloor, Integer floorDestination, Elevator elevator) {
        TypeOfTarget type = (isUserFloor) ? TypeOfTarget.USER : TypeOfTarget.DESTINATION;
        if (isApplicableNewDestinationForElevator(floorDestination, elevator)) {
            elevator.getPlannedFloors().add(new Floor(floorDestination, type));
        }
    }

    private boolean isApplicableNewDestinationForElevator(Integer floorDestination, Elevator elevator) {
        return floorDestination != null && elevator.getPlannedFloors()
                .stream()
                .filter(e2 -> Objects.equals(e2.numberOfFloor(), floorDestination))
                .toList()
                .size() == 0;
    }
}
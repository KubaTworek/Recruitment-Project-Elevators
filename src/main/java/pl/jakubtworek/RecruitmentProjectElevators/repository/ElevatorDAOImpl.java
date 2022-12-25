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
    public Elevator update(int id, int actualFloor, Integer floorDestination, boolean isUserFloor) {
        return elevators.getElevators().stream()
                .filter(e -> e.getId() == id)
                .map(e -> {
                    TypeOfTarget type = (isUserFloor == true) ? TypeOfTarget.USER : TypeOfTarget.DESTINATION;

                    e.setNumberOfFloor(actualFloor);
                    if (isApplicableNewDestinationForElevator(floorDestination, e)) {
                        e.getPlannedFloors().add(new Floor(floorDestination, type));
                    }

                    return e;
                })
                .findFirst()
                .orElse(null);
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

    private boolean isApplicableNewDestinationForElevator(Integer floorDestination, Elevator elevator) {
        return floorDestination != null && elevator.getPlannedFloors()
                .stream()
                .filter(e2 -> Objects.equals(e2.numberOfFloor(), floorDestination))
                .toList()
                .size() == 0;
    }
}
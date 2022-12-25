package pl.jakubtworek.RecruitmentProjectElevators.service;

import org.springframework.stereotype.Service;
import pl.jakubtworek.RecruitmentProjectElevators.exception.ElevatorNotFoundException;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;
import pl.jakubtworek.RecruitmentProjectElevators.repository.ElevatorDAO;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparingInt;

@Service
public class ElevatorServiceImpl implements ElevatorService {
    private final ElevatorDAO elevatorDAO;

    public ElevatorServiceImpl(ElevatorDAO elevatorDAO) {
        this.elevatorDAO = elevatorDAO;
    }

    @Override
    public void update(int id, int floor) throws ElevatorNotFoundException {
        elevatorDAO.update(id, floor);
    }

    @Override
    public void pickup(int userFloor, int destinationFloor) throws ElevatorNotFoundException {
        Elevator elevator = getProperElevator(userFloor, destinationFloor);
        int id = elevator.getId();

        if (userFloor != elevator.getNumberOfFloor()) {
            elevatorDAO.update(
                    id,
                    userFloor,
                    true
            );
        }

        elevatorDAO.update(
                id,
                destinationFloor,
                false
        );
    }

    @Override
    public void step() throws ElevatorNotFoundException {
        List<Elevator> elevators = elevatorDAO.findElevatorToMove();

        for (Elevator elevator : elevators) {
            int elevatorId = elevator.getId();

            elevatorDAO.update(
                    elevatorId
            );
        }
    }

    @Override
    public List<Elevator> status() {
        return elevatorDAO.findAll();
    }

    private Elevator getProperElevator(int userFloor, int destination) throws ElevatorNotFoundException {
        return getElevatorMovingInTheSameDirection(userFloor, destination)
                .orElse(getNearestElevator(userFloor)
                        .orElseThrow(() -> new ElevatorNotFoundException("There are not available elevator")));
    }

    private Optional<Elevator> getElevatorMovingInTheSameDirection(int userFloor, int destination) {
        return elevatorDAO.findAll().stream()
                .filter(e -> e.getPlannedFloors().peek() != null)
                .filter(e -> isElevatorsMoveInSameDirection(e, userFloor, destination))
                .findFirst();
    }

    private Optional<Elevator> getNearestElevator(int userFloor) {
        return elevatorDAO.findAll().stream()
                .filter(e -> e.getPlannedFloors().isEmpty())
                .min(comparingInt(o -> Math.abs(o.getNumberOfFloor() - userFloor)));
    }

    private boolean isElevatorsMoveInSameDirection(Elevator e, int sourceFloor, int destinationFloor) {
        return isElevatorsComingUp(e, sourceFloor, destinationFloor)
                || !isElevatorsComingUp(e, sourceFloor, destinationFloor);
    }

    private boolean isElevatorsComingUp(Elevator e, int sourceFloor, int destinationFloor) {
        return isElevatorComingUp(e.getNumberOfFloor(), e.getPlannedFloors().peek().numberOfFloor())
                && isElevatorComingUp(sourceFloor, destinationFloor);
    }

    private boolean isElevatorComingUp(int sourceFloor, int destinationFloor) {
        return destinationFloor - sourceFloor > 0;
    }
}
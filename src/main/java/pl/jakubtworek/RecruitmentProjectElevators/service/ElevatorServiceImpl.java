package pl.jakubtworek.RecruitmentProjectElevators.service;

import org.springframework.stereotype.Service;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;
import pl.jakubtworek.RecruitmentProjectElevators.model.Floor;
import pl.jakubtworek.RecruitmentProjectElevators.model.TypeOfTarget;
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
    public void pickup(int userFloor, int destinationFloor) {
        Elevator elevator = getProperElevator(userFloor, destinationFloor);
        int id = elevator.getId();
        int sourceFloor = elevator.getNumberOfFloor();

        if (userFloor != elevator.getNumberOfFloor()) {
            elevatorDAO.update(
                    id,
                    sourceFloor,
                    userFloor,
                    true
            );
        }

        elevatorDAO.update(
                id,
                sourceFloor,
                destinationFloor,
                false
        );
    }

    @Override
    public void step() {
        List<Elevator> elevators = elevatorDAO.findElevatorToMove();

        for (Elevator elevator : elevators) {
            int elevatorId = elevator.getId();
            boolean isUserFloor = false;
            Integer destination = null;
            int newFloor = elevator.getPlannedFloors().poll().numberOfFloor();

            if (elevator.getPlannedFloors().size() > 0) {
                Floor floor = elevator.getPlannedFloors().poll();
                isUserFloor = floor.typeOfTarget() == TypeOfTarget.USER;
                destination = floor.numberOfFloor();
            }

            elevatorDAO.update(
                    elevatorId,
                    newFloor,
                    destination,
                    isUserFloor
            );
        }
    }

    @Override
    public List<Elevator> status() {
        return elevatorDAO.findAll();
    }

    private Elevator getProperElevator(int userFloor, int destination) {
        return getElevatorMovingInTheSameDirection(userFloor, destination)
                .orElse(getNearestElevator(userFloor)
                        .orElse(null));
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

    private boolean isElevatorsMoveInSameDirection(Elevator e, int sourceFloor, int destinationFloor){
        return isElevatorsComingUp(e, sourceFloor, destinationFloor)
                || !isElevatorsComingUp(e, sourceFloor, destinationFloor);
    }

    private boolean isElevatorsComingUp(Elevator e, int sourceFloor, int destinationFloor){
        return isElevatorComingUp(e.getNumberOfFloor(), e.getPlannedFloors().peek().numberOfFloor())
                && isElevatorComingUp(sourceFloor, destinationFloor);
    }

    private boolean isElevatorComingUp(int sourceFloor, int destinationFloor){
        return destinationFloor - sourceFloor > 0;
    }
}
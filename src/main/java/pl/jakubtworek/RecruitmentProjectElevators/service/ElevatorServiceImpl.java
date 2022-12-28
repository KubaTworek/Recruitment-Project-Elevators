package pl.jakubtworek.RecruitmentProjectElevators.service;

import org.springframework.stereotype.Service;
import pl.jakubtworek.RecruitmentProjectElevators.exception.ElevatorNotFoundException;
import pl.jakubtworek.RecruitmentProjectElevators.factories.ElevatorFactory;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;
import pl.jakubtworek.RecruitmentProjectElevators.repository.ElevatorDAO;

import java.util.*;

import static java.util.Comparator.comparingInt;

@Service
public class ElevatorServiceImpl implements ElevatorService {
    private final ElevatorDAO elevatorDAO;
    private final ElevatorFactory elevatorFactory;

    public ElevatorServiceImpl(ElevatorDAO elevatorDAO, ElevatorFactory elevatorFactory) {
        this.elevatorDAO = elevatorDAO;
        this.elevatorFactory = elevatorFactory;
    }

    @Override
    public Elevator update(int id, int floor) throws ElevatorNotFoundException {
        Elevator elevator = elevatorDAO.findById(id)
                .orElseThrow(() -> new ElevatorNotFoundException("There are no elevator with that id: " + id));

        return elevatorFactory.create(floor).updateElevator(elevator);
    }

    @Override
    public Elevator pickup(int userFloor, int destinationFloor) throws ElevatorNotFoundException {
        Elevator elevator = getProperElevator(userFloor, destinationFloor);

        if (isMoveToUserFloor(elevator, userFloor)) {
            elevatorFactory.create(userFloor, true).updateElevator(elevator);
        }

        return elevatorFactory.create(destinationFloor, false).updateElevator(elevator);
    }

    @Override
    public List<Elevator> step() {
        List<Elevator> elevators = elevatorDAO.findElevatorToMove();

        for (Elevator elevator : elevators) {
            elevatorFactory.create().updateElevator(elevator);
        }

        return elevators;
    }

    @Override
    public List<Elevator> status() {
        return elevatorDAO.findAll();
    }

    private Elevator getProperElevator(int userFloor, int destination) throws ElevatorNotFoundException {
        return getMostApplicableElevator(userFloor, destination)
                .orElse(getNearestElevator(userFloor)
                        .orElseThrow(() -> new ElevatorNotFoundException("There are not available elevator")));
    }

    private Optional<Elevator> getMostApplicableElevator(int userFloor, int destination) {
        return elevatorDAO.findAll().stream()
                .filter(e -> !e.getPlannedFloors().isEmpty())
                .filter(e -> isElevatorsMoveInSameDirection(e, userFloor, destination))
                .filter(e -> isUserFloorIsBetweenDestinationsOfElevator(e, userFloor))
                .min(comparingInt(o -> Math.abs(o.getNumberOfFloor() - userFloor)));
    }

    private Optional<Elevator> getNearestElevator(int userFloor) {
        return elevatorDAO.findAll().stream()
                .filter(e -> e.getPlannedFloors().isEmpty())
                .min(comparingInt(e -> Math.abs(e.getNumberOfFloor() - userFloor)));
    }

    private boolean isElevatorsMoveInSameDirection(Elevator e, int sourceFloor, int destinationFloor) {
        return isElevatorsComingUp(e, sourceFloor, destinationFloor)
                || !isElevatorsComingUp(e, sourceFloor, destinationFloor);
    }

    private boolean isUserFloorIsBetweenDestinationsOfElevator(Elevator e, int sourceFloor) {
        return (sourceFloor <= e.getNumberOfFloor() && sourceFloor >= e.getPlannedFloors().peek().numberOfFloor())
                || (sourceFloor >= e.getNumberOfFloor() && sourceFloor <= e.getPlannedFloors().peek().numberOfFloor());
    }

    private boolean isElevatorsComingUp(Elevator e, int sourceFloor, int destinationFloor) {
        return isElevatorComingUp(e.getNumberOfFloor(), e.getPlannedFloors().peek().numberOfFloor())
                && isElevatorComingUp(sourceFloor, destinationFloor);
    }

    private boolean isElevatorComingUp(int sourceFloor, int destinationFloor) {
        return destinationFloor - sourceFloor > 0;
    }

    private boolean isMoveToUserFloor(Elevator elevator, int userFloor) {
        return elevator.getNumberOfFloor() != userFloor;
    }
}
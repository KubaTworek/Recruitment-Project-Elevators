package pl.jakubtworek.RecruitmentProjectElevators.service;

import org.springframework.stereotype.Service;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;
import pl.jakubtworek.RecruitmentProjectElevators.model.Floor;
import pl.jakubtworek.RecruitmentProjectElevators.model.TypeOfTarget;
import pl.jakubtworek.RecruitmentProjectElevators.repository.ElevatorDAO;

import java.util.List;

@Service
public class ElevatorServiceImpl implements ElevatorService {
    private final ElevatorDAO elevatorDAO;

    public ElevatorServiceImpl(ElevatorDAO elevatorDAO) {
        this.elevatorDAO = elevatorDAO;
    }

    @Override
    public void pickup(int userFloor, int destinationFloor) {
        Elevator elevator = getProperElevator(destinationFloor);
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

    private Elevator getProperElevator(int destination) {
        return elevatorDAO.findAll().stream()
                .filter(e -> e.getPlannedFloors().peek() != null)
                .filter(e -> e.getPlannedFloors().peek().numberOfFloor() < destination)
                .findFirst()
                .orElse(elevatorDAO.findAll().stream()
                        .filter(e -> e.getPlannedFloors().isEmpty())
                        .findFirst()
                        .orElse(null));
    }
}
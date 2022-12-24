package pl.jakubtworek.RecruitmentProjectElevators.service;

import org.springframework.stereotype.Service;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;
import pl.jakubtworek.RecruitmentProjectElevators.repository.ElevatorDAO;

import java.util.List;

// todo: comparator w queue

@Service
public class ElevatorServiceImpl implements ElevatorService {
    private final ElevatorDAO elevatorDAO;

    public ElevatorServiceImpl(ElevatorDAO elevatorDAO) {
        this.elevatorDAO = elevatorDAO;
    }

    @Override
    public void pickup(int userFloor, int destinationFloor) {
        // TODO: find closest elevator, going up
        Elevator elevator = elevatorDAO.findElevatorNotMoving().orElse(null);
        elevator.getPlannedFloors().forEach(System.out::println);
        if(userFloor != elevator.getNumberOfFloor()) {
            elevatorDAO.update(
                    elevator.getId(),
                    elevator.getNumberOfFloor(),
                    userFloor,
                    false,
                    true
            );
        }
        elevatorDAO.update(
                elevator.getId(),
                elevator.getNumberOfFloor(),
                destinationFloor,
                false,
                true
        );
    }

    @Override
    public void step() {
        List<Elevator> elevators = elevatorDAO.findElevatorToMove();
        for (Elevator elevator : elevators) {
            if (elevator.isMovingDown()) {
                elevatorDAO.update(
                        elevator.getId(),
                        elevator.getPlannedFloors().poll(),
                        null,
                        false,
                        false
                );
            } else {
                int newFloor = elevator.getPlannedFloors().poll();
                int destination = (elevator.getPlannedFloors().size() > 0) ? elevator.getPlannedFloors().poll() : 0;
                boolean isMovingUp = destination != 0;
                elevatorDAO.update(
                        elevator.getId(),
                        newFloor,
                        destination,
                        !isMovingUp,
                        isMovingUp
                );
            }
        }
    }

    @Override
    public List<Elevator> status() {
        return elevatorDAO.findAll();
    }
}
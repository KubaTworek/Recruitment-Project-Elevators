package pl.jakubtworek.RecruitmentProjectElevators.service;

import org.springframework.stereotype.Service;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;
import pl.jakubtworek.RecruitmentProjectElevators.repository.ElevatorDAO;

import java.util.List;

@Service
public class ElevatorServiceImpl implements ElevatorService {
    private final ElevatorDAO elevatorDAO;

    public ElevatorServiceImpl(ElevatorDAO elevatorDAO) {
        this.elevatorDAO = elevatorDAO;
    }

    @Override
    public void pickup(int sourceFloor, int destinationFloor) {
        Elevator elevator = elevatorDAO.findElevatorNotMoving().orElse(null);
        elevatorDAO.update(
                elevator.getId(),
                elevator.getNumberOfFloor(),
                sourceFloor,
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
                        0,
                        null,
                        false,
                        false
                );
            } else if (elevator.getPlannedFloors().size() == 1){
                int elevatorFloor = elevator.getPlannedFloors().poll();
                int userFloor = elevatorFloor;
                elevatorDAO.update(
                        elevator.getId(),
                        0,
                        0,
                        0,
                        true,
                        false
                );
            } else {
                int elevatorFloor = elevator.getPlannedFloors().poll();
                int userFloor = elevator.getPlannedFloors().poll();
                elevatorDAO.update(
                        elevator.getId(),
                        elevatorFloor,
                        userFloor,
                        userFloor,
                        false,
                        true
                );
            }
        }
    }

    @Override
    public List<Elevator> status() {
        return elevatorDAO.findAll();
    }
}
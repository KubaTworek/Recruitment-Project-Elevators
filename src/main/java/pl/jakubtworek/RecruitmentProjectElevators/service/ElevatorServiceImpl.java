package pl.jakubtworek.RecruitmentProjectElevators.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;
import pl.jakubtworek.RecruitmentProjectElevators.repository.ElevatorDAO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElevatorServiceImpl implements ElevatorService{
    private final ElevatorDAO elevatorDAO;

    @Override
    public void pickup(int sourceFloor, int destinationFloor) {
        Elevator elevator = elevatorDAO.findElevatorNotMoving().orElse(null);
        elevator.getPlannedFloors().add(destinationFloor);
        elevator.setMovingUp(true);
    }

    @Override
    public void step() {
        List<Elevator> elevators = elevatorDAO.findElevatorToMove();
        for(Elevator elevator : elevators){
            elevator.setNumberOfFloor(elevator.getPlannedFloors().poll());
            elevator.setMovingUp(false);
            elevator.getPlannedFloors().add(0);
            elevator.setMovingDown(true);
        }
    }

    @Override
    public List<Elevator> status() {
        return elevatorDAO.findAll();
    }
}

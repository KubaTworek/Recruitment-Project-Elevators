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
        for(Elevator elevator : elevators){
            if(elevator.isMovingDown()){
                elevatorDAO.update(
                        elevator.getId(),
                        elevator.getPlannedFloors().poll(),
                        null,
                        false,
                        false
                );
            } else {
                elevatorDAO.update(
                        elevator.getId(),
                        elevator.getPlannedFloors().poll(),
                        0,
                        true,
                        false
                );
            }
        }
    }

    @Override
    public List<Elevator> status() {
        return elevatorDAO.findAll();
    }
}

package pl.jakubtworek.RecruitmentProjectElevators.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElevatorDAOIT {

    @Autowired
    private ElevatorDAO elevatorDAO;

    @Test
    void shouldReturnPickedElevator() {
        // when
        elevatorDAO.pickup(4);
        List<Elevator> returnedElevators = elevatorDAO.status();

        // then
        assertEquals(0, returnedElevators.get(0).getNumberOfFloor());
        assertEquals(4, returnedElevators.get(0).getPlannedFloors().peek());
    }

    @Test
    void shouldReturnUpdatedElevator() {
        // when
        elevatorDAO.update(1, 1, 5);
        List<Elevator> returnedElevators = elevatorDAO.status();

        // then
        assertEquals(1, returnedElevators.get(0).getId());
        assertEquals(1, returnedElevators.get(0).getNumberOfFloor());
        assertEquals(5, returnedElevators.get(0).getPlannedFloors().peek());
    }

    @Test
    void shouldMoveElevatorToProperFloor() {
        // when
        elevatorDAO.pickup(4);
        List<Elevator> returnedElevators = elevatorDAO.status();
        elevatorDAO.step();

        // then
        assertEquals(4, returnedElevators.get(0).getNumberOfFloor());
        assertEquals(0, returnedElevators.get(0).getPlannedFloors().size());
    }

    @Test
    void shouldReturnAllElevators() {
        // when
        List<Elevator> returnedElevators = elevatorDAO.status();

        // then
        assertEquals(16, returnedElevators.size());
    }
}

package pl.jakubtworek.RecruitmentProjectElevators.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ElevatorDAOIT {

    @Autowired
    private ElevatorDAO elevatorDAO;

    @Test
    void shouldReturnAllElevators() {
        // when
        List<Elevator> returnedElevators = elevatorDAO.findAll();

        // then
        for (int i = 0; i < 16; i++) {
            assertEquals(i+1, returnedElevators.get(i).getId());
            assertEquals(0, returnedElevators.get(i).getNumberOfFloor());
            assertEquals(0, returnedElevators.get(i).getPlannedFloors().size());
            assertFalse(returnedElevators.get(i).isMovingDown());
            assertFalse(returnedElevators.get(i).isMovingUp());
        }
    }

    @Test
    void shouldReturnElevatorById() {
        // when
        Elevator returnedElevator = elevatorDAO.findById(1).orElse(null);

        // then
        assertEquals(1, returnedElevator.getId());
        assertEquals(0, returnedElevator.getNumberOfFloor());
        assertEquals(0, returnedElevator.getPlannedFloors().size());
        assertFalse(returnedElevator.isMovingDown());
        assertFalse(returnedElevator.isMovingUp());
    }

    @Test
    void shouldReturnNullByWrongId() {
        // when
        Elevator returnedElevator = elevatorDAO.findById(99).orElse(null);

        // then
        assertNull(returnedElevator);
    }
}

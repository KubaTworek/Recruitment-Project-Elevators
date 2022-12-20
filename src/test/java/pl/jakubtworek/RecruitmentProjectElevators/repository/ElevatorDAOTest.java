package pl.jakubtworek.RecruitmentProjectElevators.repository;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.jakubtworek.RecruitmentProjectElevators.data.*;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ElevatorDAOTest {

    @Mock
    private Elevators elevators;

    @Autowired
    private ElevatorDAO elevatorDAO;

    @BeforeEach
    void setup() {
        ElevatorsTest elevatorsTest = new ElevatorsTest();
        when(elevators.getElevators()).thenReturn(elevatorsTest.getElevators());
    }

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
    void shouldReturnNotMovingElevator() {
        // when
        Elevator returnedElevator = elevatorDAO.findElevatorNotMoving().orElse(null);

        // then
        assertEquals(1, returnedElevator.getId());
        assertEquals(0, returnedElevator.getNumberOfFloor());
        assertEquals(0, returnedElevator.getPlannedFloors().size());
        assertFalse(returnedElevator.isMovingDown());
        assertFalse(returnedElevator.isMovingUp());
    }

    @Test
    void shouldReturnAllElevatorsToMove() {
        // when
        List<Elevator> returnedElevators = elevatorDAO.findElevatorToMove();

        // then
        assertEquals(0, returnedElevators.size());
    }

    @Test
    void shouldReturnNullByWrongId() {
        // when
        Elevator returnedElevator = elevatorDAO.findById(99).orElse(null);

        // then
        assertNull(returnedElevator);
    }
}

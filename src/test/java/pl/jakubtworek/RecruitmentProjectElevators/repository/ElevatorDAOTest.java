package pl.jakubtworek.RecruitmentProjectElevators.repository;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import pl.jakubtworek.RecruitmentProjectElevators.data.*;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ElevatorDAOTest {
    @Mock
    private Elevators elevators;

    private ElevatorDAO elevatorDAO;

    @BeforeEach
    void setup() {
        elevators = mock(Elevators.class);

        elevatorDAO = new ElevatorDAOImpl(
                elevators
        );

        ElevatorsTest elevatorsTest = new ElevatorsTest();
        when(elevators.getElevators()).thenReturn(elevatorsTest.getElevators());
    }

    @Test
    void shouldReturnUpdatedElevator() {
        // when
        Elevator updatedElevator = elevatorDAO.update(1, 1, 1, 5, false, true);

        // then
        assertEquals(1, updatedElevator.getId());
        assertEquals(1, updatedElevator.getNumberOfFloor());
        assertEquals(5, updatedElevator.getPlannedFloors().peek());
        assertFalse(updatedElevator.isMovingDown());
        assertTrue(updatedElevator.isMovingUp());
    }

    @Test
    void shouldReturnAllElevators() {
        // when
        List<Elevator> returnedElevators = elevatorDAO.findAll();

        // then
        for (int i = 0; i < 16; i++) {
            assertEquals(i + 1, returnedElevators.get(i).getId());
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
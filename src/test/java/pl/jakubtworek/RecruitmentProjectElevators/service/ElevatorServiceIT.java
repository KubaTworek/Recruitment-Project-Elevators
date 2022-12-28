package pl.jakubtworek.RecruitmentProjectElevators.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.jakubtworek.RecruitmentProjectElevators.data.ElevatorsTest;
import pl.jakubtworek.RecruitmentProjectElevators.exception.ElevatorNotFoundException;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;
import pl.jakubtworek.RecruitmentProjectElevators.repository.ElevatorDAO;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class ElevatorServiceIT {
    @MockBean
    private ElevatorDAO elevatorDAO;

    @Autowired
    private ElevatorService elevatorService;

    @BeforeEach
    void setup() {
        ElevatorsTest elevatorsTest = new ElevatorsTest();
        when(elevatorDAO.findAll()).thenReturn(elevatorsTest.getElevators());
        when(elevatorDAO.findElevatorToMove()).thenReturn(elevatorsTest.getElevatorsToMove());
        when(elevatorDAO.findById(anyInt())).thenReturn(Optional.ofNullable(elevatorsTest.getElevators().get(0)));
    }

    @Test
    void shouldReturnUpdatedElevator() throws Exception {
        // when
        Elevator updatedElevator = elevatorService.update(1, 4);

        // then
        assertEquals(1, updatedElevator.getId());
        assertEquals(4, updatedElevator.getNumberOfFloor());
        assertEquals(0, updatedElevator.getPlannedFloors().size());
    }

    @Test
    void shouldThrowException_whenElevatorNotExist() {
        // when
        when(elevatorDAO.findById(anyInt())).thenReturn(Optional.empty());

        Exception exception = assertThrows(ElevatorNotFoundException.class,
                () -> elevatorService.update(-4, 4));

        // then
        assertEquals("There are no elevator with that id: -4", exception.getMessage());
    }

    @Test
    void shouldReturnElevatorToPickUp() throws Exception {
        // when
        Elevator updatedElevator = elevatorService.pickup(2, 4);

        // then
        assertEquals(1, updatedElevator.getId());
        assertEquals(0, updatedElevator.getNumberOfFloor());
        assertEquals(2, updatedElevator.getPlannedFloors().peek().numberOfFloor());
    }

    @Test
    void shouldThrowException_whenNoAvailableElevator() {
        // when
        when(elevatorDAO.findAll()).thenReturn(List.of());

        Exception exception = assertThrows(ElevatorNotFoundException.class,
                () -> elevatorService.pickup(2, 4));

        // then
        assertEquals("There are not available elevator", exception.getMessage());
    }

    @Test
    void shouldReturnElevatorsToMove() throws Exception {
        // when
        List<Elevator> elevatorsToMove = elevatorService.step();

        // then
        assertEquals(1, elevatorsToMove.get(0).getId());
        assertEquals(4, elevatorsToMove.get(0).getNumberOfFloor());
        assertEquals(5, elevatorsToMove.get(0).getPlannedFloors().peek().numberOfFloor());
        assertEquals(2, elevatorsToMove.get(1).getId());
        assertEquals(6, elevatorsToMove.get(1).getNumberOfFloor());
        assertEquals(2, elevatorsToMove.get(1).getPlannedFloors().peek().numberOfFloor());
    }

    @Test
    void shouldReturnAllElevators() {
        // when
        List<Elevator> allElevators = elevatorService.status();

        // then
        assertEquals(16, allElevators.size());
    }
}
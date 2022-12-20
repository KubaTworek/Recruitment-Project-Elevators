package pl.jakubtworek.RecruitmentProjectElevators.service;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jakubtworek.RecruitmentProjectElevators.controller.*;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;
import pl.jakubtworek.RecruitmentProjectElevators.repository.ElevatorDAO;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ElevatorServiceTest {
    @Mock
    private ElevatorDAO elevatorDAO;

    private ElevatorService elevatorService;

    @BeforeEach
    void setup() {
        elevatorDAO = mock(ElevatorDAO.class);

        elevatorService = new ElevatorServiceImpl(
                elevatorDAO
        );
    }

    @Test
    void shouldReturnPickedElevator() {
        // when
        when(elevatorDAO.findElevatorNotMoving()).thenReturn(Optional.of(new Elevator(1, 0)));
        when(elevatorDAO.findAll()).thenReturn(List.of(new Elevator(1, 0)));

        elevatorService.pickup(0, 4);
        List<Elevator> returnedElevators = elevatorService.status();

        // then
        assertEquals(0, returnedElevators.get(0).getNumberOfFloor());
        assertEquals(4, returnedElevators.get(0).getPlannedFloors().peek());
    }

    @Test
    void shouldMoveElevatorToProperFloor() {
        // when
        elevatorService.pickup(0, 4);
        List<Elevator> returnedElevators = elevatorService.status();
        elevatorService.step();

        // then
        assertEquals(4, returnedElevators.get(0).getNumberOfFloor());
        assertEquals(0, returnedElevators.get(0).getPlannedFloors().size());
    }

    @Test
    void shouldReturnAllElevators() {
        // when
        List<Elevator> returnedElevators = elevatorService.status();

        // then
        assertEquals(16, returnedElevators.size());
    }
}

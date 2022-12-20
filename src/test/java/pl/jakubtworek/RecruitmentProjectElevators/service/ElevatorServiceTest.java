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
        elevatorService.pickup(0, 4);

        // then
        verify(elevatorDAO.findElevatorNotMoving());
        verify(elevatorDAO.update(any(), any(), any(), anyBoolean(), anyBoolean()));
    }

    @Test
    void shouldMoveElevatorToProperFloor() {
        // when
        elevatorService.step();

        // then
        verify(elevatorDAO.findElevatorNotMoving());
        verify(elevatorDAO.update(any(), any(), any(), anyBoolean(), anyBoolean()));
    }

    @Test
    void shouldReturnAllElevators() {
        // when
        elevatorService.status();

        // then
        verify(elevatorDAO.findAll());
    }
}

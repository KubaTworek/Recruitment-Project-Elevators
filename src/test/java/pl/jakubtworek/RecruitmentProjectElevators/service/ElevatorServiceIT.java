/*
package pl.jakubtworek.RecruitmentProjectElevators.service;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.jakubtworek.RecruitmentProjectElevators.data.*;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ElevatorServiceIT {
    @Mock
    private Elevators elevators;

    @Autowired
    private ElevatorService elevatorService;

    @BeforeEach
    void setup() {
        ElevatorsTest elevatorsTest = new ElevatorsTest();
        when(elevators.getElevators()).thenReturn(elevatorsTest.getElevators());
    }

    @Test
    void shouldReturnPickedElevator() {
        // when
        elevatorService.pickup(0, 4);
        List<Elevator> returnedElevators = elevatorService.status();

        // then
        assertEquals(0, returnedElevators.get(0).getNumberOfFloor());
        assertEquals(4, returnedElevators.get(0).getPlannedFloors().peek());
    }

    @Test
    void shouldMoveElevatorToDestinationFloor_afterOneStep() {
        // when
        elevatorService.pickup(0, 4);
        elevatorService.step();
        List<Elevator> returnedElevators = elevatorService.status();

        // then
        assertEquals(4, returnedElevators.get(0).getNumberOfFloor());
        assertEquals(1, returnedElevators.get(0).getPlannedFloors().size());
    }

    @Test
    void shouldMoveElevatorToZeroFloor_afterTwoStep() {
        // when
        elevatorService.pickup(0, 4);
        elevatorService.step();
        elevatorService.step();
        List<Elevator> returnedElevators = elevatorService.status();

        // then
        assertEquals(0, returnedElevators.get(0).getNumberOfFloor());
        assertEquals(0, returnedElevators.get(0).getPlannedFloors().size());
    }

    @Test
    void shouldReturnAllElevators() {
        // when
        List<Elevator> returnedElevators = elevatorService.status();

        // then
        assertEquals(16, returnedElevators.size());
    }
}*/

package pl.jakubtworek.RecruitmentProjectElevators.controller;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import pl.jakubtworek.RecruitmentProjectElevators.service.ElevatorService;

import static org.mockito.Mockito.*;

public class ElevatorControllerTest {
    @Mock
    private ElevatorService elevatorService;

    private ElevatorController elevatorController;

    @BeforeEach
    void setup() {
        elevatorService = mock(ElevatorService.class);

        elevatorController = new ElevatorController(
                elevatorService
        );
    }

    @Test
    void verifyInvokingMethods_whenPickup() {
        // when
        elevatorController.pickup(0, 4);

        // then
        verify(elevatorService).pickup(0, 4);
    }

    @Test
    void verifyInvokingMethods_whenStep() {
        // when
        elevatorController.step();

        // then
        verify(elevatorService).step();
    }

    @Test
    void verifyInvokingMethods_whenStatus() {
        // when
        elevatorController.status();

        // then
        verify(elevatorService).status();
    }
}

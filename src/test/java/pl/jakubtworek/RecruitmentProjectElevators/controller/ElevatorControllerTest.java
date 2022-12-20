package pl.jakubtworek.RecruitmentProjectElevators.controller;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import pl.jakubtworek.RecruitmentProjectElevators.service.ElevatorService;

import static org.mockito.Mockito.mock;

public class ElevatorControllerTest {
    @Mock
    private ElevatorService elevatorService;

    private ElevatorController elevatorController;

    @BeforeEach
    void setup() {
        elevatorService = mock(ElevatorService.class);

        elevatorController = new ElevatorControllerImpl(
                elevatorService
        );
    }

    void pickup() {

    }

    void update() {

    }

    void step() {

    }

    void status() {

    }
}

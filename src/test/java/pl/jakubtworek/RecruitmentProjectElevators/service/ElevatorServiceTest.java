package pl.jakubtworek.RecruitmentProjectElevators.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import pl.jakubtworek.RecruitmentProjectElevators.controller.*;
import pl.jakubtworek.RecruitmentProjectElevators.repository.ElevatorDAO;

import static org.mockito.Mockito.mock;

public class ElevatorServiceTest {
    @Mock
    private ElevatorDAO elevatorDAO;

    private ElevatorService elevatorService;

    @BeforeEach
    void setup() {
        elevatorService = mock(ElevatorService.class);

        elevatorService = new ElevatorServiceImpl(
                elevatorDAO
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

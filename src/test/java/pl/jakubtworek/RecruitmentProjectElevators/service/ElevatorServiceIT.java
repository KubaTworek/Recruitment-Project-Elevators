package pl.jakubtworek.RecruitmentProjectElevators.service;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.jakubtworek.RecruitmentProjectElevators.exception.ElevatorNotFoundException;

@SpringBootTest
class ElevatorServiceIT {
    @Autowired
    private ElevatorService elevatorService;

    @BeforeEach
    void setup() {
        elevatorService.status()
                .forEach(elevator -> {
                    try {
                        elevatorService.update(elevator.getId(), 0);
                    } catch (ElevatorNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    //TODO: test scenario
}
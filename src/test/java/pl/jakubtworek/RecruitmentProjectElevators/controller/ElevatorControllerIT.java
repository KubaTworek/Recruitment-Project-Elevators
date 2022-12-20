package pl.jakubtworek.RecruitmentProjectElevators.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.jakubtworek.RecruitmentProjectElevators.service.ElevatorService;

@SpringBootTest
public class ElevatorControllerIT {
    @MockBean
    private ElevatorService elevatorService;

    @Autowired
    private ElevatorController elevatorController;

    void pickup() {

    }

    void update() {

    }

    void step() {

    }

    void status() {

    }
}
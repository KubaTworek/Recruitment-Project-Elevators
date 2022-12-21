package pl.jakubtworek.RecruitmentProjectElevators.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.jakubtworek.RecruitmentProjectElevators.data.ElevatorsTest;
import pl.jakubtworek.RecruitmentProjectElevators.repository.ElevatorDAO;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ElevatorControllerIT {
    @MockBean
    private ElevatorDAO elevatorDAO;

    @Autowired
    private ElevatorController elevatorController;

    @BeforeEach
    void setup() {
        ElevatorsTest elevatorsTest = new ElevatorsTest();
        when(elevatorDAO.findAll()).thenReturn(elevatorsTest.getElevators());
        when(elevatorDAO.findElevatorToMove()).thenReturn(elevatorsTest.getElevatorsToMove());
        when(elevatorDAO.findElevatorNotMoving()).thenReturn(Optional.ofNullable(elevatorsTest.getElevators().get(0)));
        when(elevatorDAO.findById(anyInt())).thenReturn(Optional.ofNullable(elevatorsTest.getElevators().get(0)));
        when(elevatorDAO.update(anyInt(), anyInt(), anyInt(), anyInt(), anyBoolean(), anyBoolean())).thenReturn(elevatorsTest.getElevators().get(0));
    }

    @Test
    void verifyInvokingMethods_whenPickup() {
        // when
        elevatorController.pickup(0, 4);

        // then
        verify(elevatorDAO).findElevatorNotMoving();
        verify(elevatorDAO).update(1, 0, 0, 4, false, true);
    }

    @Test
    void verifyInvokingMethods_whenStep() {
        // when
        elevatorController.step();

        // then
        verify(elevatorDAO).findElevatorToMove();
        verify(elevatorDAO, times(2)).update(anyInt(), anyInt(), anyInt(), anyInt(), anyBoolean(), anyBoolean());
    }

    @Test
    void verifyInvokingMethods_whenStatus() {
        // when
        elevatorController.status();

        // then
        verify(elevatorDAO).findAll();
    }
}
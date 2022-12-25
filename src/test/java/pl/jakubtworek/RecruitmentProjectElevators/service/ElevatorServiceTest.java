package pl.jakubtworek.RecruitmentProjectElevators.service;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import pl.jakubtworek.RecruitmentProjectElevators.data.ElevatorsTest;
import pl.jakubtworek.RecruitmentProjectElevators.repository.ElevatorDAO;

import java.util.Optional;

import static org.mockito.Mockito.*;

class ElevatorServiceTest {
    @Mock
    private ElevatorDAO elevatorDAO;

    private ElevatorService elevatorService;

    @BeforeEach
    void setup() {
        elevatorDAO = mock(ElevatorDAO.class);

        elevatorService = new ElevatorServiceImpl(
                elevatorDAO,
                elevatorFactory);

        ElevatorsTest elevatorsTest = new ElevatorsTest();
        when(elevatorDAO.findAll()).thenReturn(elevatorsTest.getElevators());
        when(elevatorDAO.findElevatorToMove()).thenReturn(elevatorsTest.getElevatorsToMove());
        when(elevatorDAO.findElevatorNotMoving()).thenReturn(Optional.ofNullable(elevatorsTest.getElevators().get(0)));
        when(elevatorDAO.findById(anyInt())).thenReturn(Optional.ofNullable(elevatorsTest.getElevators().get(0)));
        when(elevatorDAO.update(anyInt(), anyInt(), anyInt(), anyBoolean(), anyBoolean())).thenReturn(elevatorsTest.getElevators().get(0));
    }

    @Test
    void verifyInvokingMethods_whenPickup() {
        // when
        elevatorService.pickup(0, 4);

        // then
        verify(elevatorDAO).findElevatorNotMoving();
        verify(elevatorDAO).update(1, 0, 4, false, true);
    }

    @Test
    void verifyInvokingMethods_whenStep() {
        // when
        elevatorService.step();

        // then
        verify(elevatorDAO).findElevatorToMove();
        verify(elevatorDAO, times(2)).update(anyInt(), anyInt(), anyInt(), anyBoolean(), anyBoolean());
    }

    @Test
    void verifyInvokingMethods_whenStatus() {
        // when
        elevatorService.status();

        // then
        verify(elevatorDAO).findAll();
    }
}
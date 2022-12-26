package pl.jakubtworek.RecruitmentProjectElevators.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pl.jakubtworek.RecruitmentProjectElevators.data.ElevatorsTest;
import pl.jakubtworek.RecruitmentProjectElevators.exception.ElevatorNotFoundException;
import pl.jakubtworek.RecruitmentProjectElevators.factories.AdminUpdateFormula;
import pl.jakubtworek.RecruitmentProjectElevators.factories.ElevatorFactory;
import pl.jakubtworek.RecruitmentProjectElevators.factories.PickupUpdateFormula;
import pl.jakubtworek.RecruitmentProjectElevators.factories.StepUpdateFormula;
import pl.jakubtworek.RecruitmentProjectElevators.repository.ElevatorDAO;

import java.util.Optional;

import static org.mockito.Mockito.*;

class ElevatorServiceTest {
    @Mock
    private ElevatorDAO elevatorDAO;
    @Mock
    private ElevatorFactory elevatorFactory;

    private ElevatorService elevatorService;

    @BeforeEach
    void setup() {
        elevatorDAO = mock(ElevatorDAO.class);
        elevatorFactory = mock(ElevatorFactory.class);

        elevatorService = new ElevatorServiceImpl(
                elevatorDAO,
                elevatorFactory);

        ElevatorsTest elevatorsTest = new ElevatorsTest();
        when(elevatorDAO.findAll()).thenReturn(elevatorsTest.getElevatorsWithChanges());
        when(elevatorDAO.findElevatorToMove()).thenReturn(elevatorsTest.getElevatorsToMove());
        when(elevatorDAO.findById(anyInt())).thenReturn(Optional.ofNullable(elevatorsTest.getElevators().get(0)));
    }

    @Test
    void verifyInvokingMethods_whenUpdate() throws ElevatorNotFoundException {
        // when
        when(elevatorFactory.create(anyInt())).thenReturn(new AdminUpdateFormula(4));

        elevatorService.update(2, 4);

        // then
        verify(elevatorFactory, times(1)).create(4);
    }

    @Test
    void verifyInvokingMethods_whenPickup() throws ElevatorNotFoundException {
        // when
        when(elevatorFactory.create(anyInt(), anyBoolean())).thenReturn(new PickupUpdateFormula(4, false));

        elevatorService.pickup(2, 4);

        // then
        verify(elevatorFactory, times(2)).create(anyInt(), anyBoolean());
    }

    @Test
    void verifyInvokingMethods_whenPickupFromTheZeroFloor() throws ElevatorNotFoundException {
        // when
        when(elevatorFactory.create(anyInt(), anyBoolean())).thenReturn(new PickupUpdateFormula(4, true));

        elevatorService.pickup(0, 4);

        // then
        verify(elevatorFactory, times(1)).create(anyInt(), anyBoolean());
    }

    @Test
    void verifyInvokingMethods_whenStep() throws ElevatorNotFoundException {
        // when
        when(elevatorFactory.create()).thenReturn(new StepUpdateFormula());

        elevatorService.step();

        // then
        verify(elevatorDAO).findElevatorToMove();
        verify(elevatorFactory, times(2)).create();
    }

    @Test
    void verifyInvokingMethods_whenStatus() {
        // when
        elevatorService.status();

        // then
        verify(elevatorDAO).findAll();
    }
}
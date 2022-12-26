package pl.jakubtworek.RecruitmentProjectElevators.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.jakubtworek.RecruitmentProjectElevators.exception.ElevatorNotFoundException;
import pl.jakubtworek.RecruitmentProjectElevators.exception.FloorNotFoundException;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;
import pl.jakubtworek.RecruitmentProjectElevators.model.ElevatorResponse;
import pl.jakubtworek.RecruitmentProjectElevators.service.ElevatorService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void shouldReturnUpdatedElevator() throws FloorNotFoundException, ElevatorNotFoundException {
        // when
        when(elevatorService.update(anyInt(), anyInt())).thenReturn(new Elevator(2, 6));

        ResponseEntity<ElevatorResponse> response = elevatorController.update(2, 6);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().getId());
        assertEquals(6, response.getBody().getNumberOfFloor());
    }

    @Test
    void shouldReturnElevator_whichHasBeenPickedUp() throws FloorNotFoundException, ElevatorNotFoundException {
        // when
        when(elevatorService.pickup(anyInt(), anyInt())).thenReturn(new Elevator(1, 0));

        ResponseEntity<ElevatorResponse> response = elevatorController.pickup(0, 4);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getId());
        assertEquals(0, response.getBody().getNumberOfFloor());
    }

    @Test
    void shouldReturnElevators_whichHasToMove() throws ElevatorNotFoundException {
        // when
        when(elevatorService.step()).thenReturn(List.of(new Elevator(1, 0), new Elevator(2, 5)));

        ResponseEntity<List<ElevatorResponse>> response = elevatorController.step();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void shouldReturnAllElevators() {
        // when
        when(elevatorService.status()).thenReturn(List.of(new Elevator(1, 0), new Elevator(2, 5)));

        ResponseEntity<List<ElevatorResponse>> response = elevatorController.status();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }
}

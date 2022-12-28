package pl.jakubtworek.RecruitmentProjectElevators.factories;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.jakubtworek.RecruitmentProjectElevators.model.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ElevatorFactoryIT {
    @Autowired
    private ElevatorFactory elevatorFactory;

    private static Stream<Arguments> pickUpUpdate() {
        return Stream.of(
                Arguments.of(createElevator(1, 2, 5, 7), 0, true, 3, TypeOfTarget.USER),
                Arguments.of(createElevator(2, 3, 5, 7, 8), 5, true, 3, TypeOfTarget.DESTINATION),
                Arguments.of(createElevator(3, 4, 5), 2, false, 2, TypeOfTarget.DESTINATION)
        );
    }

    private static Stream<Arguments> adminUpdate() {
        return Stream.of(
                Arguments.of(createElevator(1, 2, 5, 7), 0),
                Arguments.of(createElevator(2, 3, 5, 7, 8), 1),
                Arguments.of(createElevator(3, 4, 5), 2)
        );
    }

    private static Stream<Arguments> stepUpdate() {
        return Stream.of(
                Arguments.of(createElevator(1, 2, 5, 7)),
                Arguments.of(createElevator(2, 3, 5, 7, 8)),
                Arguments.of(createElevator(3, 4, 5))
        );
    }

    private static Elevator createElevator(int id, int actualFloor, int... floors) {
        Elevator elevator = new Elevator(id, actualFloor);
        for (int floor : floors) {
            elevator.getPlannedFloors().add(new Floor(floor, TypeOfTarget.DESTINATION));
        }
        return elevator;
    }

    @ParameterizedTest
    @MethodSource("pickUpUpdate")
    void shouldReturnUpdatedElevator(Elevator elevator, int floor, boolean isUserFloor, int expectedSizeOfPlannedFloors, TypeOfTarget expectedType) {
        // when
        Elevator updatedElevator = elevatorFactory.create(floor, isUserFloor).updateElevator(elevator);

        // then
        assertEquals(expectedSizeOfPlannedFloors, updatedElevator.getPlannedFloors().size());
        assertEquals(expectedType, updatedElevator.getPlannedFloors().peek().typeOfTarget());
    }

    @ParameterizedTest
    @MethodSource("adminUpdate")
    void shouldReturnUpdatedElevator(Elevator elevator, int floor) {
        // when
        Elevator updatedElevator = elevatorFactory.create(floor).updateElevator(elevator);

        // then
        assertEquals(floor, updatedElevator.getNumberOfFloor());
        assertEquals(0, updatedElevator.getPlannedFloors().size());
    }

    @ParameterizedTest
    @MethodSource("stepUpdate")
    void shouldReturnUpdatedElevator(Elevator elevator) {
        // given
        int expectedSizeOfPlannedFloors = elevator.getPlannedFloors().size() - 1;
        int expectedFloor = elevator.getPlannedFloors().peek().numberOfFloor();

        // when
        Elevator updatedElevator = elevatorFactory.create().updateElevator(elevator);

        // then
        assertEquals(expectedSizeOfPlannedFloors, updatedElevator.getPlannedFloors().size());
        assertEquals(expectedFloor, updatedElevator.getNumberOfFloor());
    }
}
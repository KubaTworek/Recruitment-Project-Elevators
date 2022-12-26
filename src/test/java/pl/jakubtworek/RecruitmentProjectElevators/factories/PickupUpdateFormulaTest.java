package pl.jakubtworek.RecruitmentProjectElevators.factories;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;
import pl.jakubtworek.RecruitmentProjectElevators.model.Floor;
import pl.jakubtworek.RecruitmentProjectElevators.model.TypeOfTarget;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PickupUpdateFormulaTest {

    private static Stream<Arguments> elevatorsAndDestination() {
        return Stream.of(
                Arguments.of(createElevator(1, 2, 5, 7), 0, true, 3, TypeOfTarget.USER),
                Arguments.of(createElevator(2, 3, 5, 7, 8), 5, true, 3, TypeOfTarget.DESTINATION),
                Arguments.of(createElevator(3, 4, 5), 2, false, 2, TypeOfTarget.DESTINATION)
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
    @MethodSource("elevatorsAndDestination")
    void shouldReturnUpdatedElevator(Elevator elevator, int floor, boolean isUserFloor, int expectedSizeOfPlannedFloors, TypeOfTarget expectedType) {
        // given
        ElevatorFormula formula = new PickupUpdateFormula(floor, isUserFloor);

        // when
        Elevator updatedElevator = formula.updateElevator(elevator);

        // then
        assertEquals(expectedSizeOfPlannedFloors, updatedElevator.getPlannedFloors().size());
        assertEquals(expectedType, updatedElevator.getPlannedFloors().peek().typeOfTarget());
    }
}
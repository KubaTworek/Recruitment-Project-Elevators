package pl.jakubtworek.RecruitmentProjectElevators.factories;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;
import pl.jakubtworek.RecruitmentProjectElevators.model.Floor;
import pl.jakubtworek.RecruitmentProjectElevators.model.TypeOfTarget;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdminUpdateFormulaTest {

    private static Stream<Arguments> elevatorsAndDestination() {
        return Stream.of(
                Arguments.of(createElevator(1, 2, 5, 7), 0),
                Arguments.of(createElevator(2, 3, 5, 7, 8), 1),
                Arguments.of(createElevator(3, 4, 5), 2)
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
    void shouldReturnUpdatedElevator(Elevator elevator, int floor) {
        // given
        ElevatorFormula formula = new AdminUpdateFormula(floor);

        // when
        Elevator updatedElevator = formula.updateElevator(elevator);

        // then
        assertEquals(floor, updatedElevator.getNumberOfFloor());
        assertEquals(0, updatedElevator.getPlannedFloors().size());
    }
}
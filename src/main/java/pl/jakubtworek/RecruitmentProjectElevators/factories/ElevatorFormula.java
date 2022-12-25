package pl.jakubtworek.RecruitmentProjectElevators.factories;

import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;
import pl.jakubtworek.RecruitmentProjectElevators.model.Floor;
import pl.jakubtworek.RecruitmentProjectElevators.model.TypeOfTarget;

import java.util.Objects;

public interface ElevatorFormula {
    Elevator updateElevator(Elevator elevator);

    default void addNextFloor(boolean isUserFloor, Integer floorDestination, Elevator elevator) {
        TypeOfTarget type = (isUserFloor) ? TypeOfTarget.USER : TypeOfTarget.DESTINATION;
        if (isApplicableNewDestinationForElevator(floorDestination, elevator)) {
            elevator.getPlannedFloors().add(new Floor(floorDestination, type));
        }
    }

    private boolean isApplicableNewDestinationForElevator(Integer floorDestination, Elevator elevator) {
        return floorDestination != null && elevator.getPlannedFloors()
                .stream()
                .filter(e2 -> Objects.equals(e2.numberOfFloor(), floorDestination))
                .toList()
                .size() == 0;
    }
}

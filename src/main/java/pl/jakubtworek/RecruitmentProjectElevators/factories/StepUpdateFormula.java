package pl.jakubtworek.RecruitmentProjectElevators.factories;

import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;
import pl.jakubtworek.RecruitmentProjectElevators.model.Floor;
import pl.jakubtworek.RecruitmentProjectElevators.model.TypeOfTarget;

public class StepUpdateFormula implements ElevatorFormula {

    public StepUpdateFormula() {
    }

    @Override
    public Elevator updateElevator(Elevator elevator) {
        boolean isUserFloor = false;
        Integer floorDestination = null;
        int newFloor = elevator.getPlannedFloors().poll().numberOfFloor();
        if (elevator.getPlannedFloors().size() > 0) {
            Floor floor = elevator.getPlannedFloors().poll();
            isUserFloor = floor.typeOfTarget() == TypeOfTarget.USER;
            floorDestination = floor.numberOfFloor();
        }

        elevator.setNumberOfFloor(newFloor);
        addNextFloor(isUserFloor, floorDestination, elevator);

        return elevator;
    }
}
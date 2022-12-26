package pl.jakubtworek.RecruitmentProjectElevators.factories;

import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

public class PickupUpdateFormula implements ElevatorFormula {
    private final Integer floorDestination;
    private final boolean isUserFloor;

    public PickupUpdateFormula(Integer floorDestination, boolean isUserFloor) {
        this.floorDestination = floorDestination;
        this.isUserFloor = isUserFloor;
    }

    @Override
    public Elevator updateElevator(Elevator elevator) {
        addNextFloor(isUserFloor, floorDestination, elevator);

        return elevator;
    }
}
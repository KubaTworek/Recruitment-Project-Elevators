package pl.jakubtworek.RecruitmentProjectElevators.factories;

import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

public class AdminUpdateFormula implements ElevatorFormula {
    private final int floor;
    public AdminUpdateFormula(int floor) {
        this.floor = floor;
    }

    @Override
    public Elevator updateElevator(Elevator elevator) {
        elevator.setNumberOfFloor(floor);
        elevator.getPlannedFloors().clear();

        return elevator;
    }
}

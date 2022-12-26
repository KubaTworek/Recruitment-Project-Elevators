package pl.jakubtworek.RecruitmentProjectElevators.factories;

import org.springframework.stereotype.Component;

@Component
public class ElevatorFactory {
    public ElevatorFormula create(
            Integer floorDestination,
            boolean isUserFloor
    ) {
        return new PickupUpdateFormula(floorDestination, isUserFloor);
    }

    public ElevatorFormula create(
            int floor
    ) {
        return new AdminUpdateFormula(floor);
    }

    public ElevatorFormula create() {
        return new StepUpdateFormula();
    }
}
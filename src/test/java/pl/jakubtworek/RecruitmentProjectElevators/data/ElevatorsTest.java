package pl.jakubtworek.RecruitmentProjectElevators.data;

import pl.jakubtworek.RecruitmentProjectElevators.model.*;

import java.util.*;

public class ElevatorsTest {
    public ElevatorsTest() {
    }

    public List<Elevator> getElevators() {
        List<Elevator> elevators = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            elevators.add(new Elevator(
                    i + 1,
                    0
            ));
        }

        return elevators;
    }

    public List<Elevator> getElevatorsWithChanges() {
        List<Elevator> elevators = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            elevators.add(new Elevator(
                    i + 1,
                    0
            ));
        }

        elevators.get(1).setNumberOfFloor(8);
        elevators.get(0).getPlannedFloors().add(new Floor(4, TypeOfTarget.USER));
        elevators.get(1).getPlannedFloors().add(new Floor(6, TypeOfTarget.DESTINATION));

        return elevators;
    }

    public List<Elevator> getElevatorsToMove() {
        List<Elevator> elevators = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            elevators.add(new Elevator(
                    i + 1,
                    0
            ));
        }

        elevators.get(1).setNumberOfFloor(8);
        elevators.get(0).getPlannedFloors().add(new Floor(4, TypeOfTarget.USER));
        elevators.get(1).getPlannedFloors().add(new Floor(6, TypeOfTarget.DESTINATION));
        elevators.get(0).getPlannedFloors().add(new Floor(5, TypeOfTarget.DESTINATION));
        elevators.get(1).getPlannedFloors().add(new Floor(2, TypeOfTarget.DESTINATION));

        return elevators;
    }
}
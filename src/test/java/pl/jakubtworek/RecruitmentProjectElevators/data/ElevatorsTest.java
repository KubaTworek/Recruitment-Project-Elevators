package pl.jakubtworek.RecruitmentProjectElevators.data;

import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;
import pl.jakubtworek.RecruitmentProjectElevators.model.Floor;
import pl.jakubtworek.RecruitmentProjectElevators.model.TypeOfTarget;

import java.util.ArrayList;
import java.util.List;

public class ElevatorsTest {
    private final List<Elevator> elevators = new ArrayList<>();
    private final List<Elevator> elevatorsWithChanges = new ArrayList<>();
    private final List<Elevator> elevatorsToMove = new ArrayList<>();

    public ElevatorsTest() {
    }

    public List<Elevator> getElevators() {
        for (int i = 0; i < 16; i++) {
            elevators.add(new Elevator(
                    i + 1,
                    0
            ));
        }

        return elevators;
    }

    public List<Elevator> getElevatorsWithChanges() {
        for (int i = 0; i < 16; i++) {
            elevatorsWithChanges.add(new Elevator(
                    i + 1,
                    0
            ));
        }

        elevatorsWithChanges.get(1).setNumberOfFloor(8);
        elevatorsWithChanges.get(0).getPlannedFloors().add(new Floor(4, TypeOfTarget.USER));
        elevatorsWithChanges.get(1).getPlannedFloors().add(new Floor(6, TypeOfTarget.DESTINATION));

        return elevatorsWithChanges;
    }

    public List<Elevator> getElevatorsToMove() {
        for (int i = 0; i < 2; i++) {
            elevatorsToMove.add(new Elevator(
                    i + 1,
                    0
            ));
        }

        elevatorsToMove.get(1).setNumberOfFloor(8);
        elevatorsToMove.get(0).getPlannedFloors().add(new Floor(4, TypeOfTarget.USER));
        elevatorsToMove.get(1).getPlannedFloors().add(new Floor(6, TypeOfTarget.DESTINATION));
        elevatorsToMove.get(0).getPlannedFloors().add(new Floor(5, TypeOfTarget.DESTINATION));
        elevatorsToMove.get(1).getPlannedFloors().add(new Floor(2, TypeOfTarget.DESTINATION));

        return elevatorsToMove;
    }

}
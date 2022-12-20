package pl.jakubtworek.RecruitmentProjectElevators.data;

import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

import java.util.*;

public class ElevatorsTest {
    private final List<Elevator> elevators = new ArrayList<>();
    private final List<Elevator> elevatorsToMove = new ArrayList<>();

    public ElevatorsTest() {}

    public List<Elevator> getElevators() {
        for(int i = 0; i < 16; i++){
            elevators.add(new Elevator(
                    i+1,
                    0
            ));
        }
        return elevators;
    }

    public List<Elevator> getElevatorsToMove() {
        for(int i = 0; i < 2; i++){
            elevatorsToMove.add(new Elevator(
                    i+1,
                    0
            ));
            elevatorsToMove.get(i).getPlannedFloors().add(i+1);
            elevatorsToMove.get(i).setMovingUp(true);
        }
        return elevatorsToMove;
    }
}
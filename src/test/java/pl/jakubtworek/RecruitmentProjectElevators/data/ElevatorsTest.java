package pl.jakubtworek.RecruitmentProjectElevators.data;

import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

import java.util.*;

public class ElevatorsTest {
    private final List<Elevator> elevators = new ArrayList<>();

    public ElevatorsTest() {
        for(int i = 0; i < 16; i++){
            elevators.add(new Elevator(
                    i+1,
                    0
            ));
        }
    }

    public void restart(){
        elevators.clear();
        for(int i = 0; i < 16; i++){
            elevators.add(new Elevator(
                    i+1,
                    0
            ));
        }
    }

    public List<Elevator> getElevators() {
        return elevators;
    }
}
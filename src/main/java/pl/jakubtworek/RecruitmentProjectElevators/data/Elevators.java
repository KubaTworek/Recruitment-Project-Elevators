package pl.jakubtworek.RecruitmentProjectElevators.data;

import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

import java.util.*;

public class Elevators {
    private final List<Elevator> elevators = new ArrayList<>();
    private static volatile Elevators instance = null;

    private Elevators() {
        if(instance != null){
            throw new RuntimeException("Not allowed. Please use getInstance() method");
        }
        for(int i = 0; i < 16; i++){
            elevators.add(new Elevator(
                    i+1,
                    0
            ));
        }
    }

    public static Elevators getInstance(){
        if(instance == null){
            synchronized (Elevators.class) {
                if(instance == null) {
                    instance = new Elevators();
                }
            }
        }
        return instance;
    }

    public List<Elevator> getElevators() {
        return elevators;
    }
}
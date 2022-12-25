package pl.jakubtworek.RecruitmentProjectElevators.data;

import org.springframework.stereotype.Component;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

import java.util.ArrayList;
import java.util.List;

@Component
public class Elevators {
    private final List<Elevator> elevators = new ArrayList<>();

    public Elevators() {
        for (int i = 0; i < 16; i++) {
            elevators.add(new Elevator(
                    i + 1,
                    0
            ));
        }
    }

    public List<Elevator> getElevators() {
        return elevators;
    }
}
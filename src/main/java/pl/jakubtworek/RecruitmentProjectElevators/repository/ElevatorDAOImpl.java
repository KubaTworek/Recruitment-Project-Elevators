package pl.jakubtworek.RecruitmentProjectElevators.repository;

import org.springframework.stereotype.Repository;
import pl.jakubtworek.RecruitmentProjectElevators.data.Elevators;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

import java.util.List;

@Repository
public class ElevatorDAOImpl implements ElevatorDAO{
    private final Elevators elevators = Elevators.getInstance();

    @Override
    public void pickup(int destinationFloor) {

    }

    @Override
    public Elevator update(int id, int numberOfFloor, int destinationFloor) {
        return null;
    }

    @Override
    public void step() {

    }

    @Override
    public List<Elevator> status() {
        return null;
    }
}

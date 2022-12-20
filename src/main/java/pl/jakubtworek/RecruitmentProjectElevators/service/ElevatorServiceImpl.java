package pl.jakubtworek.RecruitmentProjectElevators.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;
import pl.jakubtworek.RecruitmentProjectElevators.repository.ElevatorDAO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElevatorServiceImpl implements ElevatorService{
    private final ElevatorDAO elevatorDAO;

    @Override
    public void pickup() {

    }

    @Override
    public Elevator update() {
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

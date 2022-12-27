package pl.jakubtworek.RecruitmentProjectElevators.repository;

import pl.jakubtworek.RecruitmentProjectElevators.model.Elevator;

import java.util.*;

public interface ElevatorDAO {
    List<Elevator> findAll();

    Optional<Elevator> findById(int id);

    List<Elevator> findElevatorToMove();
}
package pl.jakubtworek.RecruitmentProjectElevators.model;

import lombok.*;

import java.util.*;

@Getter
@Setter
public class Elevator {
    private int id;
    private int numberOfFloor;
    private boolean isMovingUp;
    private boolean isMovingDown;
    private Queue<Elevator> plannedFloors;

    public Elevator(int id, int numberOfFloor) {
        this.id = id;
        this.numberOfFloor = numberOfFloor;
        this.isMovingUp = false;
        this.isMovingDown = false;
        this.plannedFloors = new PriorityQueue<>();
    }
}
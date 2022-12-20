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
    private Queue<Integer> plannedFloors;

    public Elevator(int id, int numberOfFloor) {
        this.id = id;
        this.numberOfFloor = numberOfFloor;
        this.isMovingUp = false;
        this.isMovingDown = false;
        this.plannedFloors = new PriorityQueue<>();
    }

    public ElevatorResponse convertToResponse() {
        if (this.getPlannedFloors().size() == 0) {
            return ElevatorResponse.builder()
                    .id(id)
                    .numberOfFloor(numberOfFloor)
                    .numberOfClosestDestination(null)
                    .build();
        } else {
            return ElevatorResponse.builder()
                    .id(id)
                    .numberOfFloor(numberOfFloor)
                    .numberOfClosestDestination(plannedFloors.peek())
                    .build();
        }
    }
}
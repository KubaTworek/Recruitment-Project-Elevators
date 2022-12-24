package pl.jakubtworek.RecruitmentProjectElevators.model;

import java.util.*;

public class Elevator {
    private final int id;
    private int numberOfFloor;
    private boolean isMovingUp;
    private boolean isMovingDown;
    private final Queue<Integer> plannedFloors;

    public Elevator(int id, int numberOfFloor) {
        this.id = id;
        this.numberOfFloor = numberOfFloor;
        this.isMovingUp = false;
        this.isMovingDown = false;
        this.plannedFloors = new PriorityQueue<>(new FloorComparator());
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

    public int getId() {
        return this.id;
    }

    public int getNumberOfFloor() {
        return this.numberOfFloor;
    }

    public boolean isMovingUp() {
        return this.isMovingUp;
    }

    public boolean isMovingDown() {
        return this.isMovingDown;
    }

    public Queue<Integer> getPlannedFloors() {
        return this.plannedFloors;
    }

    public void setNumberOfFloor(int numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }

    public void setMovingUp(boolean isMovingUp) {
        this.isMovingUp = isMovingUp;
    }

    public void setMovingDown(boolean isMovingDown) {
        this.isMovingDown = isMovingDown;
    }
}
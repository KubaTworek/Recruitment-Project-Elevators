package pl.jakubtworek.RecruitmentProjectElevators.model;

import java.util.*;

public class Elevator {
    private final int id;
    private final Queue<Floor> plannedFloors;
    private int numberOfFloor;

    public Elevator(int id, int numberOfFloor) {
        this.id = id;
        this.numberOfFloor = numberOfFloor;
        this.plannedFloors = new PriorityQueue<>(new FloorComparator());
    }

    public ElevatorResponse convertToResponse() {
        Floor destinationFloor = plannedFloors.peek();
        Integer destination = null;
        if (destinationFloor != null) {
            destination = destinationFloor.numberOfFloor();
        }

        return ElevatorResponse.builder()
                .id(id)
                .numberOfFloor(numberOfFloor)
                .numberOfClosestDestination(destination)
                .build();
    }

    public int getId() {
        return this.id;
    }

    public int getNumberOfFloor() {
        return this.numberOfFloor;
    }

    public void setNumberOfFloor(int numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }

    public Queue<Floor> getPlannedFloors() {
        return this.plannedFloors;
    }
}
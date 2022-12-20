package pl.jakubtworek.RecruitmentProjectElevators.model;

import lombok.*;

import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Elevator {
    private int id;
    private int numberOfFloor;
    private boolean isMovingUp = false;
    private boolean isMovingDown = false;
    private Queue plannedFloors = new PriorityQueue();
}
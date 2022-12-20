package pl.jakubtworek.RecruitmentProjectElevators.model;

import lombok.*;

@Data
@Builder
public class ElevatorResponse {
    private int id;
    private int numberOfFloor;
    private Integer numberOfClosestDestination;
}
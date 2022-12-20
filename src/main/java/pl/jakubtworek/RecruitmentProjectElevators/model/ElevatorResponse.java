package pl.jakubtworek.RecruitmentProjectElevators.model;

public class ElevatorResponse {
    private final int id;
    private final int numberOfFloor;
    private final Integer numberOfClosestDestination;

    ElevatorResponse(int id, int numberOfFloor, Integer numberOfClosestDestination) {
        this.id = id;
        this.numberOfFloor = numberOfFloor;
        this.numberOfClosestDestination = numberOfClosestDestination;
    }

    public static ElevatorResponseBuilder builder() {
        return new ElevatorResponseBuilder();
    }

    public int getId() {
        return this.id;
    }

    public int getNumberOfFloor() {
        return this.numberOfFloor;
    }

    public Integer getNumberOfClosestDestination() {
        return this.numberOfClosestDestination;
    }

    public static class ElevatorResponseBuilder {
        private int id;
        private int numberOfFloor;
        private Integer numberOfClosestDestination;

        ElevatorResponseBuilder() {
        }

        public ElevatorResponseBuilder id(int id) {
            this.id = id;
            return this;
        }

        public ElevatorResponseBuilder numberOfFloor(int numberOfFloor) {
            this.numberOfFloor = numberOfFloor;
            return this;
        }

        public ElevatorResponseBuilder numberOfClosestDestination(Integer numberOfClosestDestination) {
            this.numberOfClosestDestination = numberOfClosestDestination;
            return this;
        }

        public ElevatorResponse build() {
            return new ElevatorResponse(id, numberOfFloor, numberOfClosestDestination);
        }
    }
}
package pl.jakubtworek.RecruitmentProjectElevators.model;

public class Floor {
    public enum TypeOfTarget {
        USER, DESTINATION
    }
    private static int INDEX = 0;
    private final int id = INDEX++;
    private Integer numberOfFloor;
    private TypeOfTarget typeOfTarget;

    public Floor(int numberOfFloor, TypeOfTarget typeOfTarget) {
        this.numberOfFloor = numberOfFloor;
        this.typeOfTarget = typeOfTarget;
    }

    public int getId() {
        return id;
    }

    public Integer getNumberOfFloor() {
        return numberOfFloor;
    }

    public void setNumberOfFloor(int numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }

    public TypeOfTarget getTypeOfTarget() {
        return typeOfTarget;
    }

    public void setTypeOfTarget(TypeOfTarget typeOfTarget) {
        this.typeOfTarget = typeOfTarget;
    }
}

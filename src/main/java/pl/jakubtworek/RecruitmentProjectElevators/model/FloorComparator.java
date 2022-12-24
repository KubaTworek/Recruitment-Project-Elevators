package pl.jakubtworek.RecruitmentProjectElevators.model;

import java.util.Comparator;

public class FloorComparator implements Comparator<Floor> {

    @Override
    public int compare(Floor f1, Floor f2) {
        if (f1.getTypeOfTarget().compareTo(f2.getTypeOfTarget()) != 0) {
            return f1.getTypeOfTarget().compareTo(f2.getTypeOfTarget());
        } else {
            return f1.getNumberOfFloor().compareTo(f2.getNumberOfFloor());
        }
    }
}

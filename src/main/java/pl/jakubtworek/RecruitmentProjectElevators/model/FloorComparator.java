package pl.jakubtworek.RecruitmentProjectElevators.model;

import java.util.Comparator;

public class FloorComparator implements Comparator<Floor> {

    @Override
    public int compare(Floor f1, Floor f2) {
        return f1.typeOfTarget().compareTo(f2.typeOfTarget());
    }
}
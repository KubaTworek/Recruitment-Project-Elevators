package pl.jakubtworek.RecruitmentProjectElevators.model;

import java.util.Comparator;

public class FloorComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
}

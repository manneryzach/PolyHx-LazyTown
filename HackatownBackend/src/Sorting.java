import java.util.ArrayList;

public class Sorting {
    public static <E extends Comparable> void quickSort(ArrayList<E> elements, int left, int right) {
        if (left < right) {
            int i = placeAndDivide(elements, left, right);
            quickSort(elements, left, i - 1);
            quickSort(elements, i + 1, right);
        }
    }

    private static <E extends Comparable> int placeAndDivide(ArrayList<E> elements, int left, int right) {
        // Pick ~median as pivot (3 points)
        placeMedian(elements, left, right);
        E pivot = elements.get(right);

        int wall = left - 1;

        for (int i = left; i < right; i++) {
            if (elements.get(i).compareTo(pivot) < 0) {
                wall++;
                if (i != wall) swap(elements, i, wall);
            }
        }
        if (right != wall + 1) swap(elements, right, wall + 1);

        return wall + 1;
    }

    private static <E extends Comparable> void placeMedian(ArrayList<E> elements, int left, int right) {
        E low = elements.get(left);
        E mid = elements.get((left + right) / 2);
        E high = elements.get(right);

        // Note that if high is the median, then the pivot is already correctly placed (no swap).
        if (low.compareTo(mid) <= 0 && low.compareTo(high) <= 0 && mid.compareTo(high) < 0) {
            swap(elements, (left + right) / 2, right);
//            return mid;
        }
        else if (mid.compareTo(low) <= 0 && mid.compareTo(high) <= 0 && low.compareTo(high) < 0) {
            swap(elements, left, right);
//            return low;
        }
        else if (high.compareTo(mid) <= 0 && high.compareTo(low) <= 0) {
            if (low.compareTo(mid) < 0) {
                swap(elements, (left + right) / 2, right);
//                return mid;
            }
            else {
                swap(elements, left, right);
//                return low;
            }
        }
//        return high;
    }

    public static <E> void swap(ArrayList<E> elements, int index1, int index2) {
        E temp = elements.get(index1);
        elements.set(index1, elements.get(index2));
        elements.set(index2, temp);
    }
}


package present.programmer.algorithms.sandbox.sort.util;

import static present.programmer.algorithms.sandbox.sort.util.Compare.less;

public class Median {

    @SuppressWarnings("Duplicates")
    public static <T extends Comparable<T>> int of3(final T[] array, final int aIndex, final int bIndex, final int cIndex) {
        final T a = array[aIndex];
        final T b = array[bIndex];
        final T c = array[cIndex];
        if (less(a, b)) {
            if (less(b, c)) {
                return bIndex;
            } else if (less(a, c)) {
                return cIndex;
            } else {
                return aIndex;
            }
        } else if (less(c, b)) {
            return bIndex;
        } else if (less(a, c)) {
            return aIndex;
        } else {
            return cIndex;
        }
    }
}

package present.programmer.algorithms.sandbox.sort.util;

public class Compare {

    public static <T> boolean less(final Comparable<T> first, final T second) {
        return first.compareTo(second) < 0;
    }

    public static <T> boolean lessOrEquals(final Comparable<T> first, final T second) {
        return first.compareTo(second) <= 0;
    }

    public static <T> boolean greater(final Comparable<T> first, final T second) {
        return first.compareTo(second) > 0;
    }
}

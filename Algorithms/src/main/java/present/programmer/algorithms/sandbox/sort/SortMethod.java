package present.programmer.algorithms.sandbox.sort;

public abstract class SortMethod<T extends Comparable<T>> {

    public abstract T[] sort(T[] arr);

    protected static <T> void swap(final T[] arr, final int i, final int j) {
        final T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    protected static <T extends Comparable<T>> boolean less(final T a, final T b) {
        return a.compareTo(b) < 0;
    }
}

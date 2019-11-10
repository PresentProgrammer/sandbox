package present.programmer.algorithms.sandbox.sort;

public class InsertionSort<T extends Comparable<T>> extends SortMethod<T> {

    @Override
    public T[] sort(final T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j - 1 >= 0 && less(arr[j], arr[j - 1])) {
                swap(arr, j, j - 1);
                j--;
            }
        }
        return arr;
    }
}

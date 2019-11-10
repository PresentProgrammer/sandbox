package present.programmer.algorithms.sandbox.sort;

public class SelectionSort<T extends Comparable<T>> extends SortMethod<T> {

    @Override
    public T[] sort(final T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minInd = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (less(arr[j], arr[minInd])) {
                    minInd = j;
                }
            }
            swap(arr, i, minInd);
        }
        return arr;
    }
}

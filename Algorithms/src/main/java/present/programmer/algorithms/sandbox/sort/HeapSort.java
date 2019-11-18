package present.programmer.algorithms.sandbox.sort;

import static java.lang.System.arraycopy;

public class HeapSort<T extends Comparable<T>> extends SortMethod<T> {

    private T[] arr;

    @Override
    public T[] sort(final T[] arr) {
        this.arr = copyWithShiftByOne(arr);
        constructHeap();
        heapToSortedArray();
        return copyBackTo(arr);
    }

    private void constructHeap() {
        for (int k = (arr.length - 1) / 2; k > 0; k--) {
            sink(k, arr.length);
        }
    }

    private void heapToSortedArray() {
        for (int i = arr.length - 1; i > 1; i--) {
            swap(arr, 1, i);
            sink(1, i);
        }
    }

    private void sink(int k, final int size) {
        while (k * 2 < size) {
            int j = k * 2;
            if (j + 1 < size && less(arr[j], arr[j + 1])) {
                j++;
            }
            if (less(arr[k], arr[j])) {
                swap(arr, k, j);
                k = j;
            } else {
                return;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> T[] copyWithShiftByOne(final T[] arr) {
        final T[] copy = (T[]) new Comparable[arr.length + 1];
        arraycopy(arr, 0, copy, 1, arr.length);
        return copy;
    }

    private T[] copyBackTo(final T[] arr) {
        arraycopy(this.arr, 1, arr, 0, arr.length);
        return arr;
    }
}

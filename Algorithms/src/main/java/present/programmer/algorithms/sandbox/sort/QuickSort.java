package present.programmer.algorithms.sandbox.sort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Random;

public class QuickSort<T extends Comparable<T>> extends SortMethod<T> {

    private T[] arr;

    @Override
    public T[] sort(final T[] arr) {
        this.arr = arr;
        shuffle();
        sort(0, arr.length - 1);
        return arr;
    }

    private void sort(final int lo, final int hi) {
        if (lo < hi) {
            final int j = partition(lo, hi);
            sort(lo, j - 1);
            sort(j + 1, hi);
        }
    }

    private int partition(final int lo, final int hi) {
        int i = lo + 1;
        int j = hi;
        while (true) {
            while (i <= hi && less(arr[i], arr[lo])) {
                i++;
            }
            while (less(arr[lo], arr[j])) {
                j--;
            }
            if (i < j) {
                swap(arr, i, j);
                i++;
                j--;
            } else {
                swap(arr, lo, j);
                return j;
            }
        }
    }

    private void shuffle() {
        final Random random = new Random();
        for (int i = 1; i < arr.length; i++) {
            swap(arr, i, random.nextInt(i + 1));
        }
    }
}

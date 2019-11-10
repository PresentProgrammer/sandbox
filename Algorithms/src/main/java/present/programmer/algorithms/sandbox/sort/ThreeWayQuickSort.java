package present.programmer.algorithms.sandbox.sort;

import java.util.Random;

public class ThreeWayQuickSort<T extends Comparable<T>> extends SortMethod<T> {

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
            int lt = lo;
            int gt = hi;
            int i = lt + 1;
            while (i <= gt) {
                final int cmp = arr[i].compareTo(arr[lt]);
                if (cmp < 0) {
                    swap(arr, lt++, i++);
                } else if (cmp > 0) {
                    swap(arr, i, gt--);
                } else {
                    i++;
                }
            }
            sort(lo, lt - 1);
            sort(gt + 1, hi);
        }
    }

    private void shuffle() {
        final Random random = new Random();
        for (int i = 1; i < arr.length; i++) {
            swap(arr, i, random.nextInt(i + 1));
        }
    }
}

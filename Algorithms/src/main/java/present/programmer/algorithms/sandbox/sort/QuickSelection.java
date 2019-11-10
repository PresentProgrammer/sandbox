package present.programmer.algorithms.sandbox.sort;

import java.util.Arrays;
import java.util.Random;

public class QuickSelection<T extends Comparable<T>> extends SortMethod<T> {

    private T[] arr;
    private int k;

    /**
     * @param k1 one-based k
     */
    public T[] selectFirstKElements(final T[] arr, final int k1) {
        this.arr = arr;
        this.k = k1 - 1; // convert to zero-based.
        shuffle();
        select();
        return Arrays.copyOf(arr, k1);
    }

    /**
     *
     * @param k0 zero-based k
     */
    public T selectKElement(final T[] arr, final int k0) {
        selectFirstKElements(arr, k0 + 1);
        return arr[k];
    }

    @Override
    public T[] sort(final T[] arr) {
        throw new UnsupportedOperationException("Use other methods.");
    }

    private void select() {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo < hi) {
            final int j = partition(lo, hi);
            if (j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                return;
            }
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

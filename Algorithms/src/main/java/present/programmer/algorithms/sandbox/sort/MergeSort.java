package present.programmer.algorithms.sandbox.sort;

public class MergeSort<T extends Comparable<T>> extends SortMethod<T> {

    private T[] auxArr;
    private T[] arr;

    @Override
    @SuppressWarnings("unchecked")
    public T[] sort(final T[] arr) {
        this.arr = arr;
        this.auxArr = (T[]) new Comparable[arr.length];
        sort(0, arr.length - 1);
        return arr;
    }

    private void sort(final int lo, final int hi) {
        if (lo < hi) {
            final int mid = (lo + hi) >>> 1;
            sort(lo, mid);
            sort(mid + 1, hi);
            merge(lo, hi, mid);
        }
    }

    private void merge(final int lo, final int hi, final int mid) {
        System.arraycopy(arr, lo, auxArr, lo, hi - lo + 1);
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                return; // since right part is copied from arr
            } else if (j > hi) {
                arr[k] = auxArr[i++];
            } else if (less(auxArr[i], auxArr[j])) {
                arr[k] = auxArr[i++];
            } else {
                arr[k] = auxArr[j++];
            }
        }
    }
}

package present.programmer.algorithms.sandbox.sort;

public class ShellSort<T extends Comparable<T>> extends SortMethod<T> {

    /**
     * 3x + 1 is used for the sequence, i.e., 1, 4, 13, 40, ...
     */
    private static final int SEQ_COEF = 3;

    @Override
    public T[] sort(final T[] arr) {
        int h = 1;
        while (h < arr.length) {
            h = h * SEQ_COEF + 1;
        }

        while (h > 0) {
            for (int i = h; i < arr.length; i++) {
                int j = i;
                while (j - h >= 0 && less(arr[j], arr[j - h])) {
                    swap(arr, j, j - h);
                    j -= h;
                }
            }
            h /= SEQ_COEF;
        }
        return arr;
    }
}

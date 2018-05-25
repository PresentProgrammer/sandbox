package present.programmer.algorithms.sandbox.sort;

public class MergeSortAsInCourse extends SortMethod {

    @Override
    <T extends Comparable<T>> void applySortingMethodTo(final T[] array) {
        new MergeSorter<>(array).sort();
    }

    private static class MergeSorter<T extends Comparable<T>> extends MergeSort.MergeSorter<T> {

        private MergeSorter(final T[] unsortedArray) {
            super(unsortedArray);
        }

        @Override
        void mergeFromAuxiliaryArray(final int begin, final int mid, final int end) {
            int i = begin;
            int j = mid;
            for (int k = begin; k < end; k++) {
                if (i >= mid) {
                    result[k] = auxiliaryArray[j++];
                } else if (j >= end) {
                    result[k] = auxiliaryArray[i++];
                } else if (lessOrEquals(auxiliaryArray[i], auxiliaryArray[j])) {
                    result[k] = auxiliaryArray[i++];
                } else {
                    result[k] = auxiliaryArray[j++];
                }
            }
        }
    }
}

package present.programmer.algorithms.sandbox.collection.queue;

import java.util.NoSuchElementException;

import static java.lang.System.arraycopy;

public class PriorityQueue<E extends Comparable<E>> {

    private static final int INITIAL_CAPACITY = 4;
    private static final int SHRINK_THRESHOLD = 4;
    private static final int GROW_AND_SHRINK_COEF = 2;
    private static final int FIRST = 1;

    @SuppressWarnings("unchecked")
    private E[] arr = (E[]) new Comparable[INITIAL_CAPACITY];

    /**
     * 0th element in array is kept untouched to simplify arithmetic (see notes on Binary Heap).
     */
    private int size = FIRST;

    public void enqueue(final E elem) {
        if (elem == null) {
            throw new IllegalArgumentException();
        }
        ensureCapacity();
        arr[size] = elem;
        swim(size);
        size++;
    }

    public E dequeue() {
        if (size == FIRST) {
            throw new NoSuchElementException();
        }
        final E max = arr[FIRST];
        arr[FIRST] = arr[--size];
        arr[size] = null;
        avoidMemoryLeak();
        sink();
        return max;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            swap(k, k / 2);
            k /= 2;
        }
    }

    private void sink() {
        int k = 1;
        while (k * 2 < size) {
            int j = k * 2;
            if (j + 1 < size && less(j, j + 1)) {
                j++;
            }
            if (less(k, j)) {
                swap(k, j);
                k = j;
            } else {
                return;
            }
        }
    }

    private boolean less(final int i, final int j) {
        return arr[i].compareTo(arr[j]) < 0;
    }

    private void swap(final int i, final int j) {
        final E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void ensureCapacity() {
        if (size + 1 == arr.length) {
            resize(arr.length * GROW_AND_SHRINK_COEF);
        }
    }

    private void avoidMemoryLeak() {
        if ((size + 1) * SHRINK_THRESHOLD <= arr.length && arr.length >= INITIAL_CAPACITY * GROW_AND_SHRINK_COEF) {
            resize(arr.length / GROW_AND_SHRINK_COEF);
        }
    }

    @SuppressWarnings("unchecked")
    private void resize(final int newCapacity) {
        final E[] newArray = (E[]) new Comparable[newCapacity];
        arraycopy(arr, 0, newArray, 0, size);
        arr = newArray;
    }
}

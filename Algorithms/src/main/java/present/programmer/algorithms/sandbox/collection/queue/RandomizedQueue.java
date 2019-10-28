package present.programmer.algorithms.sandbox.collection.queue;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.lang.System.arraycopy;

@SuppressWarnings("WeakerAccess")
public class RandomizedQueue<E> implements Queue<E> {

    private static final int INITIAL_CAPACITY = 4;
    private static final int SHRINK_THRESHOLD = 4;
    private static final int GROW_AND_SHRINK_COEF = 2;

    private E[] items;
    private int size;

    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        this.items = (E[]) new Object[INITIAL_CAPACITY];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(E item) {
        ensureCapacity();
        items[size++] = requireNonNull(item);
    }

    /**
     * Remove and return a random item
     */
    public E dequeue() {
        requireNotEmpty();
        final int dequeueIndex = StdRandom.uniform(size--);
        final E dequeueItem = items[dequeueIndex];
        items[dequeueIndex] = items[size];
        avoidLoitering();
        avoidMemoryLeak();
        return dequeueItem;
    }

    public E sample() {
        requireNotEmpty();
        return items[StdRandom.uniform(size)];
    }

    /**
     * Return an independent iterator over items in random order
     */
    public Iterator<E> iterator() {
        return new IteratorImpl<>(items, size);
    }

    private void ensureCapacity() {
        if (size == items.length) {
            resize(items.length * GROW_AND_SHRINK_COEF);
        }
    }

    private void avoidMemoryLeak() {
        if (size * SHRINK_THRESHOLD <= items.length && items.length >= INITIAL_CAPACITY * GROW_AND_SHRINK_COEF) {
            resize(items.length / GROW_AND_SHRINK_COEF);
        }
    }

    @SuppressWarnings("unchecked")
    private void resize(final int newCapacity) {
        final E[] newArray = (E[]) new Object[newCapacity];
        arraycopy(items, 0, newArray, 0, size);
        items = newArray;
    }

    private void avoidLoitering() {
        items[size] = null;
    }

    private void requireNotEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    private static <E> E requireNonNull(final E item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        return item;
    }

    private static class IteratorImpl<E> implements Iterator<E> {

        private final E[] shuffledItems;
        private int nextIndex;

        private IteratorImpl(final E[] items, final int size) {
            this.shuffledItems = shuffle(copy(items, size));
        }

        @Override
        public boolean hasNext() {
            return nextIndex < shuffledItems.length;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return shuffledItems[nextIndex++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @SuppressWarnings("unchecked")
        private static <E> E[] copy(final E[] items, final int size) {
            final E[] copy = (E[]) new Object[size];
            System.arraycopy(items, 0, copy, 0, size);
            return copy;
        }

        private static <E> E[] shuffle(final E[] items) {
            for (int i = 0; i < items.length - 1; i++) {
                swap(items, i, i + StdRandom.uniform(items.length - i));
            }
            return items;
        }

        private static <E> void swap(final E[] items, final int first, final int second) {
            final E firstItem = items[first];
            items[first] = items[second];
            items[second] = firstItem;
        }
    }
}

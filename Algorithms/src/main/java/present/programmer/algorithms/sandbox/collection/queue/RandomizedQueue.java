package present.programmer.algorithms.sandbox.collection.queue;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("WeakerAccess")
public class RandomizedQueue<Item> implements Queue<Item> {

    private static final int INITIAL_CAPACITY = 4;
    private static final int SHRINK_THRESHOLD = 4;
    private static final int GROW_AND_SHRINK_COEF = 2;

    private Item[] items;
    private int size;

    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        this.items = (Item[]) new Object[INITIAL_CAPACITY];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        ensureCapacity();
        items[size++] = requireNonNull(item);
    }

    /**
     * Remove and return a random item
     */
    public Item dequeue() {
        requireNotEmpty();
        final int dequeueIndex = StdRandom.uniform(size--);
        final Item dequeueItem = items[dequeueIndex];
        items[dequeueIndex] = items[size];
        avoidLoitering();
        avoidMemoryLeak();
        return dequeueItem;
    }

    public Item sample() {
        requireNotEmpty();
        return items[StdRandom.uniform(size)];
    }

    /**
     * Return an independent iterator over items in random order
     */
    public Iterator<Item> iterator() {
        return new IteratorImpl<>(items, size);
    }

    public static void main(String[] args) {
        System.out.println("Tested separately");
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
        final Item[] newArray = (Item[]) new Object[newCapacity];
        System.arraycopy(items, 0, newArray, 0, size);
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

    private static <Item> Item requireNonNull(final Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        return item;
    }

    private static class IteratorImpl<Item> implements Iterator<Item> {

        private final Item[] shuffledItems;
        private int nextIndex;

        private IteratorImpl(final Item[] items, final int size) {
            this.shuffledItems = shuffle(copy(items, size));
        }

        @Override
        public boolean hasNext() {
            return nextIndex < shuffledItems.length;
        }

        @Override
        public Item next() {
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
        private static <Item> Item[] copy(final Item[] items, final int size) {
            final Item[] copy = (Item[]) new Object[size];
            System.arraycopy(items, 0, copy, 0, size);
            return copy;
        }

        private static <Item> Item[] shuffle(final Item[] items) {
            for (int i = 1; i < items.length; i++) {
                swap(items, i, StdRandom.uniform(i + 1));
            }
            return items;
        }

        private static <Item> void swap(final Item[] items, final int first, final int second) {
            final Item firstItem = items[first];
            items[first] = items[second];
            items[second] = firstItem;
        }
    }
}

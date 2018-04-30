package present.programmer.algorithms.sandbox.collection.queue;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("WeakerAccess")
public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int INITIAL_CAPACITY = 4;

    private Item[] items;
    private int size;

    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        items = (Item[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        requireNonNull(item);
        ensureEnoughCapacity();
        items[size++] = item;
    }

    public Item dequeue() {
        requireNotEmpty();
        final Item item = items[--size];
        items[size] = null;
        ensureCapacityNotTooBig();
        return item;
    }

    public Item sample() {
        requireNotEmpty();
        return items[StdRandom.uniform(size)];
    }

    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private static <E> void requireNonNull(final E item) {
        if (item == null) {
            throw new IllegalArgumentException("item must not be null");
        }
    }

    private void requireNotEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException("cannot call on empty collection");
        }
    }

    private void ensureEnoughCapacity() {
        if (size == items.length) {
            grow();
        }
    }

    private void ensureCapacityNotTooBig() {
        if (size < items.length / 4) {
            shrink();
        }
    }

    private void grow() {
        resize(items.length * 2);
    }

    private void shrink() {
        resize(items.length / 2);
    }

    @SuppressWarnings({"unchecked", "ManualArrayCopy"})
    private void resize(final int newSize) {
        final Item[] newArray = (Item[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newArray[i] = items[i];
        }
        items = newArray;
    }

    private class RandomizedIterator implements Iterator<Item> {

        private final int[] randomizedIndexes;
        private int nextIndex;

        RandomizedIterator() {
            randomizedIndexes = sequenceFromZeroToN(size);
            StdRandom.shuffle(randomizedIndexes);
            nextIndex = 0;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                return items[randomizedIndexes[nextIndex++]];
            } else {
                throw new NoSuchElementException("there are no more items to return");
            }
        }

        @Override
        public boolean hasNext() {
            return nextIndex < randomizedIndexes.length;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private int[] sequenceFromZeroToN(final int n) {
            final int[] sequence = new int[n];
            for (int i = 0; i < n; i++) {
                sequence[i] = i;
            }
            return sequence;
        }
    }
}

package present.programmer.algorithms.sandbox.collection.queue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings({"unused", "WeakerAccess"})
public class ArrayBasedQueue<E> implements Queue<E> {

    private static final int INITIAL_CAPACITY = 4;

    private E[] items;
    private int head;
    private int tail;

    @SuppressWarnings("unchecked")
    public ArrayBasedQueue() {
        this.items = (E[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void enqueue(final E item) {
        ensureEnoughCapacity();
        items[tail] = item;
        tail = circularlyIncrement(tail);
    }

    @Override
    public E dequeue() {
        requireNotEmpty();
        final E item = items[head];
        items[head] = null;
        head = circularlyIncrement(head);
        ensureCapacityNotTooBig();
        return item;
    }

    @Override
    public int size() {
        if (head == tail) {
            return isEmpty() ? 0 : items.length;
        } else if (tail > head) {
            return tail - head;
        } else {
            return items.length - head + tail;
        }
    }

    @Override
    public boolean isEmpty() {
        return items[head] == null;
    }

    @Override
    public E sample() {
        requireNotEmpty();
        return items[head];
    }

    @Override
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    public String toString() {
        return "items = " + Arrays.toString(items) + ", head = " + head + ", tail = " + tail;
    }

    private void ensureEnoughCapacity() {
        if (head == tail && !isEmpty()) {
            grow();
        }
    }

    private void grow() {
        resize(items.length * 2);
    }

    private void ensureCapacityNotTooBig() {
        int size = size();
        if (size <= items.length / 4) {
            shrink();
        }
    }

    private void shrink() {
        resize(items.length / 2);
    }

    private void resize(final int newSize) {
        final int size = size();
        items = newArrayWithCopiedElements(newSize);
        head = 0;
        tail = size;
    }

    @SuppressWarnings("unchecked")
    private E[] newArrayWithCopiedElements(final int newSize) {
        final E[] newArray = (E[]) new Object[newSize];
        int i = 0;
        for (final E item : this) {
            newArray[i++] = item;
        }
        return newArray;
    }

    private int circularlyIncrement(final int index) {
        return (index + 1) % items.length;
    }

    private void requireNotEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException("cannot call on empty queue");
        }
    }

    private class QueueIterator implements Iterator<E> {

        private int count;

        public boolean hasNext() {
            return count < size();
        }

        public E next() {
            return items[circularIndex(count++)];
        }

        private int circularIndex(final int i) {
            return (head + i) % items.length;
        }
    }
}

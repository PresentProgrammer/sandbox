package present.programmer.algorithms.sandbox.collection.stack;

import java.util.Iterator;

import static java.lang.System.arraycopy;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ArrayBasedStack<T> implements Iterable<T> {

    private static final int INITIAL_CAPACITY = 4;

    private T[] elements;
    private int currentSize;

    @SuppressWarnings("unchecked")
    public ArrayBasedStack(final int size) {
        elements = (T[]) new Object[size];
        currentSize = 0;
    }

    public ArrayBasedStack() {
        this(INITIAL_CAPACITY);
    }

    public void push(T element) {
        ensureEnoughCapacity();
        elements[currentSize++] = element;
    }

    public T pop() {
        final T result = elements[--currentSize];
        avoidLoitering();
        ensureCapacityNotTooBig();
        return result;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public int size() {
        return currentSize;
    }

    @Override
    public Iterator<T> iterator() {
        return new PrimitiveIterator();
    }

    private void ensureEnoughCapacity() {
        if (currentSize == elements.length) {
            grow();
            System.out.println("[INFO] Stack capacity grew to " + elements.length);
        }
    }

    private void ensureCapacityNotTooBig() {
        if (currentSize < elements.length / 4) {
            shrink();
            System.out.println("[INFO] Stack capacity shrank to " + elements.length);
        }
    }

    private void grow() {
        resize(elements.length * 2);
    }


    private void shrink() {
        resize(elements.length / 2);
    }

    @SuppressWarnings("unchecked")
    private void resize(final int newSize) {
        final T[] newArray = (T[]) new Object[newSize];
        arraycopy(elements, 0, newArray, 0, currentSize);
        elements = newArray;
    }

    private void avoidLoitering() {
        elements[currentSize] = null;
    }

    private class PrimitiveIterator implements Iterator<T> {

        private int nextIndex;

        PrimitiveIterator() {
            this.nextIndex = currentSize;
        }

        @Override
        public boolean hasNext() {
            return nextIndex > 0;
        }

        @Override
        public T next() {
            return elements[--nextIndex];
        }
    }
}

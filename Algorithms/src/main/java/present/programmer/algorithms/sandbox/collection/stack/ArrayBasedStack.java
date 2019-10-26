package present.programmer.algorithms.sandbox.collection.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.lang.System.arraycopy;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ArrayBasedStack<E> implements Stack<E> {

    private static final int INITIAL_CAPACITY = 4;
    private static final int SHRINK_THRESHOLD = 4;
    private static final int GROW_AND_SHRINK_COEF = 2;

    private E[] elements;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayBasedStack(final int initialCapacity) {
        this.elements = (E[]) new Object[initialCapacity];
    }

    public ArrayBasedStack() {
        this(INITIAL_CAPACITY);
    }

    @Override
    public void push(final E element) {
        ensureCapacity();
        elements[size++] = element;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        final E element = elements[--size];
        avoidLoitering();
        avoidMemoryLeak();
        return element;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorImpl();
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            resize(elements.length * GROW_AND_SHRINK_COEF);
        }
    }

    private void avoidMemoryLeak() {
        if (size * SHRINK_THRESHOLD < elements.length && elements.length > INITIAL_CAPACITY * GROW_AND_SHRINK_COEF) {
            resize(elements.length / GROW_AND_SHRINK_COEF);
        }
    }

    @SuppressWarnings("unchecked")
    private void resize(final int newCapacity) {
        final E[] newArray = (E[]) new Object[newCapacity];
        arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
        System.out.println("New stack capacity: " + elements.length);
    }

    private void avoidLoitering() {
        elements[size] = null;
    }

    private class IteratorImpl implements Iterator<E> {

        private int prevIndex = size;

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elements[--prevIndex];
        }

        @Override
        public boolean hasNext() {
            return prevIndex > 0;
        }
    }
}

package present.programmer.algorithms.sandbox.collection.stack;

@SuppressWarnings({"WeakerAccess", "unused"})
public class FixedCapacityStack<T> {

    private T[] elements;
    private int currentSize;

    @SuppressWarnings("unchecked")
    public FixedCapacityStack(final int size) {
        elements = (T[]) new Object[size];
        currentSize = 0;
    }

    public void push(T element) {
        elements[currentSize++] = element;
    }

    public T pop() {
        return elements[--currentSize];
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public int size() {
        return currentSize;
    }
}

package present.programmer.algorithms.sandbox.collection.stack;

@SuppressWarnings("WeakerAccess")
public class FixedCapacityStack<T> {

    private Object[] elements;
    private int currentSize;

    public FixedCapacityStack(final int size) {
        elements = new Object[size];
        currentSize = 0;
    }

    public void push(T element) {
        elements[currentSize++] = element;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        return (T) elements[--currentSize];
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public int size() {
        return currentSize;
    }
}

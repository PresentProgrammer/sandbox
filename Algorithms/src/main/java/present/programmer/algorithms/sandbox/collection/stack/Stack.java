package present.programmer.algorithms.sandbox.collection.stack;

public interface Stack<E> extends Iterable<E> {

    void push(E element);
    E pop();
    boolean isEmpty();
    int size();
}

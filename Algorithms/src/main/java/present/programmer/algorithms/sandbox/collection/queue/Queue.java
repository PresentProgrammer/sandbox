package present.programmer.algorithms.sandbox.collection.queue;

public interface Queue<E> extends Iterable<E> {

    void enqueue(E item);

    E dequeue();

    boolean isEmpty();

    int size();

    E sample();
}

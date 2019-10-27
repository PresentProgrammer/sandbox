package present.programmer.algorithms.sandbox.collection.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListQueue<E> implements Queue<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;

    @Override
    public void enqueue(final E item) {
        final Node<E> oldLast = last;
        last = new Node<>(item);
        if (oldLast == null) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        final E item = first.item;
        first = first.next;
        if (first == null) {
            last = null;
        }
        size--;
        return item;
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
    public E sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return first.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorImpl<>(first);
    }

    private static class Node<E> {

        private final E item;
        private Node<E> next;

        private Node(final E item) {
            this.item = item;
        }
    }

    private static class IteratorImpl<E> implements Iterator<E> {

        private Node<E> nextNode;

        private IteratorImpl(final Node<E> first) {
            this.nextNode = first;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            final E item = nextNode.item;
            nextNode = nextNode.next;
            return item;
        }
    }
}

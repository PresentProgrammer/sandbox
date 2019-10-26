package present.programmer.algorithms.sandbox.collection.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("WeakerAccess")
public class LinkedListBasedStack<E> implements Stack<E> {

    private Node<E> head;
    private int currentSize;

    @Override
    public void push(final E element) {
        head = new Node<>(element, head);
        currentSize++;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        final E result = head.item;
        head = head.next;
        currentSize--;
        return result;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public Iterator<E> iterator() {
        return new PrimitiveIterator<>(head);
    }

    private static class Node<E> {

        E item;
        Node<E> next;

        Node(final E item, final Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    private static class PrimitiveIterator<E> implements Iterator<E> {

        private Node<E> nextNode;

        PrimitiveIterator(final Node<E> head) {
            this.nextNode = head;
        }

        @Override
        public E next() {
            final E result = nextNode.item;
            nextNode = nextNode.next;
            return result;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }
    }
}

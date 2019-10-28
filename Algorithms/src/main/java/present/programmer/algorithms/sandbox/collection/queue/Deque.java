package present.programmer.algorithms.sandbox.collection.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("WeakerAccess")
public class Deque<E> implements Iterable<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(final E item) {
        requireNonNull(item);
        if (isEmpty()) {
            addTheVeryFirstItem(item);
        } else {
            first = new Node<>(item, first, null);
            first.next.prev = first;
        }
        size++;
    }

    public void addLast(final E item) {
        requireNonNull(item);
        if (isEmpty()) {
            addTheVeryFirstItem(item);
        } else {
            last = new Node<>(item, null, last);
            last.prev.next = last;
        }
        size++;
    }

    public E removeFirst() {
        requireNotEmpty();
        final E item = first.item;
        first = first.next;
        updateStateAfterRemove();
        return item;
    }

    public E removeLast() {
        requireNotEmpty();
        final E item = last.item;
        last = last.prev;
        updateStateAfterRemove();
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<E> iterator() {
        return new IteratorImpl<>(first);
    }

    private void addTheVeryFirstItem(final E item) {
        first = new Node<>(item, null, null);
        last = first;
    }

    private void updateStateAfterRemove() {
        size--;
        if (isEmpty()) {
            first = null;
            last = null;
        } else {
            first.prev = null;
            last.next = null;
        }
    }

    private void requireNotEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    private static <E> void requireNonNull(final E item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    private static class IteratorImpl<E> implements Iterator<E> {

        private Node<E> nextNode;

        private IteratorImpl(final Node<E> nextNode) {
            this.nextNode = nextNode;
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

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static class Node<E> {

        private final E item;
        private Node<E> next;
        private Node<E> prev;

        private Node(final E item, final Node<E> next, final Node<E> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}

package present.programmer.algorithms.sandbox.collection.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("WeakerAccess")
public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        final Node<Item> oldFirst = first;
        first = new Node<>(requireNonNull(item), null, oldFirst);
        if (size == 0) {
            last = first;
        } else {
            oldFirst.previous = first;
        }
        size++;
    }

    public void addLast(Item item) {
        final Node<Item> oldLast = last;
        last = new Node<>(requireNonNull(item), oldLast, null);
        if (size == 0) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    public Item removeFirst() {
        tryDecrementingSize();
        final Item item = first.item;
        first = first.next;
        handleEmptyDeque_first();
        return item;
    }

    public Item removeLast() {
        tryDecrementingSize();
        final Item item = last.item;
        last = last.previous;
        handleEmptyDeque_last();
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private static <E> E requireNonNull(final E item) {
        if (item == null) {
            throw new IllegalArgumentException("item must not be null");
        }
        return item;
    }

    private void tryDecrementingSize() {
        if (size == 0) {
            throw new NoSuchElementException();
        } else {
            size--;
        }
    }

    private void handleEmptyDeque_first() {
        if (first != null) {
            first.previous = null;
        } else {
            last = null;
        }
    }

    private void handleEmptyDeque_last() {
        if (last != null) {
            last.next = null;
        } else {
            first = null;
        }
    }

    private static class Node<E> {

        E item;
        Node<E> previous;
        Node<E> next;

        Node(final E item, final Node<E> previous, final Node<E> next) {
            this.item = item;
            this.previous = previous;
            this.next = next;
        }
    }

    private class DequeIterator implements Iterator<Item> {

        private Node<Item> nextNode;

        DequeIterator() {
            this.nextNode = first;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                final Item result = nextNode.item;
                nextNode = nextNode.next;
                return result;
            } else {
                throw new NoSuchElementException("there are no more items to return");
            }
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

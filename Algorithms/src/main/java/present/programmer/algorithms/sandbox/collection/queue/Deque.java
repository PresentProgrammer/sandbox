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

    public void addFirst(final Item item) {
        requireNonNull(item);
        if (isEmpty()) {
            addTheVeryFirstItem(item);
        } else {
            first = new Node<>(item, first, null);
            first.next.prev = first;
        }
        size++;
    }

    public void addLast(final Item item) {
        requireNonNull(item);
        if (isEmpty()) {
            addTheVeryFirstItem(item);
        } else {
            last = new Node<>(item, null, last);
            last.prev.next = last;
        }
        size++;
    }

    public Item removeFirst() {
        requireNotEmpty();
        final Item item = first.item;
        first = first.next;
        updateStateAfterRemove();
        return item;
    }

    public Item removeLast() {
        requireNotEmpty();
        final Item item = last.item;
        last = last.prev;
        updateStateAfterRemove();
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new IteratorImpl<>(first);
    }

    public static void main(String[] args) {
        System.out.println("Tested separately");
    }

    private void addTheVeryFirstItem(final Item item) {
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

    private static <Item> void requireNonNull(final Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    private static class IteratorImpl<Item> implements Iterator<Item> {

        private Node<Item> nextNode;

        private IteratorImpl(final Node<Item> nextNode) {
            this.nextNode = nextNode;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            final Item item = nextNode.item;
            nextNode = nextNode.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static class Node<Item> {

        private final Item item;
        private Node<Item> next;
        private Node<Item> prev;

        private Node(final Item item, final Node<Item> next, final Node<Item> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}

package present.programmer.algorithms.sandbox.collection.queue;

import java.util.Scanner;

public class DequeClient {

    public static void main(final String... args) {
        final Deque<String> deque = new Deque<>();
        interactWith(deque);
    }

    private static void interactWith(final Deque<String> deque) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                processInput(scanner, deque);
            }
        }
    }

    private static void processInput(final Scanner scanner, final Deque<String> deque) {
        final String choice = scanner.next();
        if ("addFirst".equals(choice)) {
            deque.addFirst(scanner.next());
        } else if ("addLast".equals(choice)) {
            deque.addLast(scanner.next());
        } else if ("removeFirst".equals(choice)) {
            System.out.println(deque.removeFirst());
        } else if ("removeLast".equals(choice)) {
            System.out.println(deque.removeLast());
        } else if ("print".equals(choice)) {
            print(deque);
        } else if ("size".equals(choice)) {
            System.out.println(deque.size());
        } else if ("isEmpty".equals(choice)) {
            System.out.println(deque.isEmpty());
        } else {
            deque.addFirst(scanner.next());
        }
    }

    private static void print(final Iterable<String> iterable) {
        for (final String element : iterable) {
            System.out.println(element);
        }
    }
}

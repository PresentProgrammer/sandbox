package present.programmer.algorithms.sandbox.collection.queue;

import java.util.Scanner;

public class RandomizedQueueClient {

    public static void main(final String... args) {
        final RandomizedQueue<String> queue = new RandomizedQueue<>();
        interactWith(queue);
    }

    private static void interactWith(final RandomizedQueue<String> queue) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                processInput(scanner, queue);
            }
        }
    }

    private static void processInput(final Scanner scanner, final RandomizedQueue<String> queue) {
        final String choice = scanner.next();
        if ("enqueue".equals(choice)) {
            queue.enqueue(scanner.next());
        } else if ("dequeue".equals(choice)) {
            System.out.println(queue.dequeue());
        } else if ("sample".equals(choice)) {
            System.out.println(queue.sample());
        } else if ("print".equals(choice)) {
            print(queue);
        } else if ("size".equals(choice)) {
            System.out.println(queue.size());
        } else if ("isEmpty".equals(choice)) {
            System.out.println(queue.isEmpty());
        } else {
            queue.enqueue(scanner.next());
        }
    }

    private static void print(final Iterable<String> iterable) {
        for (final String element : iterable) {
            System.out.println(element);
        }
    }
}

package present.programmer.algorithms.sandbox.collection.queue;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Permutation {

    public static void main(String[] args) {
        final int k = Integer.parseInt(args[0]);
        final RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        populateFromStdInput(randomizedQueue);
        printPermutationOf(k, randomizedQueue);
    }

    private static void populateFromStdInput(final RandomizedQueue<String> randomizedQueue) {
        while (!StdIn.isEmpty()) {
            randomizedQueue.enqueue(StdIn.readString());
        }
    }

    private static void printPermutationOf(final int k, final RandomizedQueue<String> randomizedQueue) {
        final Iterator<String> iterator = randomizedQueue.iterator();
        for (int i = 0; i < k; i++) {
            StdOut.println(iterator.next());
        }
    }
}

package present.programmer.multithreading.memory.consistency;

import java.util.concurrent.CyclicBarrier;

/**
 * Testing an integer increment by multiple threads.
 * - For primitive int, the result differs from the expected.
 * - 'volatile' keyword doesn't help here.
 * - AtomicInteger however does great job here.
 */
public class Counter {

    private static final int INCREMENT_NUMBER = 10000;
    private static final int THREAD_NUMBER = 20;
    private static final int THIS_THREAD = 1;

    private static volatile int counter = 0;

    public static void main(String[] args) {
        final CyclicBarrier barrier = new CyclicBarrier(THREAD_NUMBER + THIS_THREAD);
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(new Incrementer(barrier)).start();
        }
        waitForThreadsToFinish(barrier);
        printResults();
    }

    private static void waitForThreadsToFinish(final CyclicBarrier barrier) {
        try {
            barrier.await();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void printResults() {
        System.out.println("counter is expected to be = " + INCREMENT_NUMBER * THREAD_NUMBER);
        System.out.println("actual value = " + counter);
    }

    private static class Incrementer implements Runnable {

        private final CyclicBarrier barrier;

        private Incrementer(final CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            for (int j = 0; j < INCREMENT_NUMBER; j++) {
                counter++;
            }
            reachBarrier();
        }

        private void reachBarrier() {
            try {
                barrier.await();
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

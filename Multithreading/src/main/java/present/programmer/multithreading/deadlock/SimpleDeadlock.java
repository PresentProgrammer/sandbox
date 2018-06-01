package present.programmer.multithreading.deadlock;

import static java.lang.Thread.currentThread;

/**
 * Implementation guarantees a deadlock by making two threads wait for each other to free the used resource.
 * Notice that the main thread is waiting for the secondThread to finish, which means that main thread
 * sees the second one as the resource.
 */
public class SimpleDeadlock {

    public static void main(String[] args) {
        final SimpleDeadlock lockedObject = new SimpleDeadlock();
        lockedObject.lockedMethod();
    }

    private synchronized void lockedMethod() {
        System.out.println(currentThread().getName() + " entered lockedMethod()");
        Thread secondThread = new Thread(() -> {
            System.out.println(currentThread().getName() + " started; will try to call lockedMethod()");
            this.lockedMethod();
        });
        secondThread.start();
        waitFor(secondThread);
    }

    private void waitFor(final Thread secondThread) {
        System.out.println("waiting for " + secondThread.getName() + " to finish");
        try {
            secondThread.join();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        thisLineIsNeverReached(secondThread);
    }

    private void thisLineIsNeverReached(final Thread secondThread) {
        System.out.println(secondThread.getName() + " finished");
    }
}
